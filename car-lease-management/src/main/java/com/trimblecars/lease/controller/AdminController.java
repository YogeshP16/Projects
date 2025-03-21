import com.trimblecars.lease.model.Car;
import com.trimblecars.lease.model.User;
import com.trimblecars.lease.repository.CarRepository;
import com.trimblecars.lease.repository.LeaseRepository;
import com.trimblecars.lease.repository.UserRepository;
import com.trimblecars.lease.service.CarService;
import com.trimblecars.lease.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CarService carService;

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private UserRepository userRepository;

    // Admin can perform all operations available to Car Owner and End Customer
    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/leases")
    public List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }

    @PostMapping("/cars/register")
    public Car registerCar(@RequestBody Car car, @RequestParam Long ownerId) {
        User owner = userRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("User not found"));
        return carService.registerCar(car, owner);
    }

    @GetMapping("/healthCheck")
    public String getHealth(){
        return "Running";
    }
}