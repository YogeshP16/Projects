package com.trimblecars.lease.service;

import com.trimblecars.lease.dto.LeaseDTO;
import com.trimblecars.lease.model.Car;
import com.trimblecars.lease.model.Lease;
import com.trimblecars.lease.model.User;
import com.trimblecars.lease.repository.CarRepository;
import com.trimblecars.lease.repository.LeaseRepository;
import com.trimblecars.lease.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LeaseService {

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    public LeaseDTO startLease(Long carId, Long customerId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with ID: " + carId));

        if (car.getStatus() != CarStatus.IDLE) {
            throw new IllegalStateException("Car is not available for lease");
        }

        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));

        Lease lease = new Lease();
        lease.setCar(car);
        lease.setCustomer(customer);
        lease.setLeaseStartDate(LocalDate.now());
        lease.setLeaseEndDate(null);

        car.setStatus(CarStatus.LEASED);
        carRepository.save(car);

        Lease savedLease = leaseRepository.save(lease);
        return new LeaseDTO(savedLease.getId(), savedLease.getCar().getId(), savedLease.getCustomer().getId(),
                savedLease.getLeaseStartDate(), savedLease.getLeaseEndDate());
    }

    public void endLease(Long leaseId) {
        Lease lease = leaseRepository.findById(leaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Lease not found with ID: " + leaseId));

        lease.setLeaseEndDate(LocalDate.now());
        leaseRepository.save(lease);

        Car car = lease.getCar();
        car.setStatus(CarStatus.IDLE);
        carRepository.save(car);
    }
}
