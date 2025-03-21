package com.trimblecars.lease.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private String status; // 'Ideal', 'On Lease', 'On Service'

    @ManyToOne
    private User owner;  // Car Owner (relation to User)

    @OneToMany(mappedBy = "car")
    private List<Lease> leases;

    public Car() {
    }

    public Car(Long id, String make, String model, String status, User owner, List<Lease> leases) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.status = status;
        this.owner = owner;
        this.leases = leases;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setLeases(List<Lease> leases) {
        this.leases = leases;
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getStatus() {
        return status;
    }

    public User getOwner() {
        return owner;
    }

    public List<Lease> getLeases() {
        return leases;
    }
}