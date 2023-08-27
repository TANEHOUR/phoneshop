package com.piseth.java.school.phoneshop.phoneshop.repository;

import com.piseth.java.school.phoneshop.phoneshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
