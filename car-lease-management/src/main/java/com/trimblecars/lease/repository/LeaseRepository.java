package com.trimblecars.lease.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.dgc.Lease;

public interface LeaseRepository extends JpaRepository<Lease, Long> { }