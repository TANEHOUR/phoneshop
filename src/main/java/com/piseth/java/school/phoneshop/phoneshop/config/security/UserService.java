package com.piseth.java.school.phoneshop.phoneshop.config.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    Optional<AuthUser> findUserByUsername(String username);
}
