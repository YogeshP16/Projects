package com.trimblecars.lease.repository;

import com.trimblecars.lease.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> { }