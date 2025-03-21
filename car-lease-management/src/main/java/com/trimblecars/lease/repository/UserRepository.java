package com.trimblecars.lease.repository;

import com.trimblecars.lease.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }