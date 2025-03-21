package com.trimblecars.lease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leases")
public class LeaseController {

    @Autowired
    private LeaseService leaseService;

    @PostMapping("/start")
    public Lease startLease(@RequestParam Long carId, @RequestParam Long customerId) {
        return leaseService.startLease(carId, customerId);
    }

    @PostMapping("/end")
    public Lease endLease(@RequestParam Long leaseId) {
        return leaseService.endLease(leaseId);
    }

    @GetMapping("/history/{customerId}")
    public List<Lease> getLeaseHistory(@PathVariable Long customerId) {
        return leaseService.getLeaseHistory(customerId);
    }
}