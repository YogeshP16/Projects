package com.trimblecars.lease.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

import java.rmi.dgc.Lease;
import java.util.List;

@Entity
@Table(name = "CUSTOM_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;  // 'CAR_OWNER', 'END_CUSTOMER', 'ADMIN'

    @OneToMany(mappedBy = "owner")
    private List<Car> cars;

    @OneToMany(mappedBy = "customer")
    private List<Lease> leases;

    public User() {
    }

    public User(Long id, String username, String password, String role, List<Car> cars, List<Lease> leases) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.cars = cars;
        this.leases = leases;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setLeases(List<Lease> leases) {
        this.leases = leases;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Lease> getLeases() {
        return leases;
    }
}
