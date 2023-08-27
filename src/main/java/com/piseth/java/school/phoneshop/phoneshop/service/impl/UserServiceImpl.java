package com.piseth.java.school.phoneshop.phoneshop.service.impl;

import com.piseth.java.school.phoneshop.phoneshop.config.security.AuthUser;
import com.piseth.java.school.phoneshop.phoneshop.config.security.UserService;
import com.piseth.java.school.phoneshop.phoneshop.entities.User;
import com.piseth.java.school.phoneshop.phoneshop.exception.ApiException;
import com.piseth.java.school.phoneshop.phoneshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<AuthUser> findUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));
        AuthUser authUser = AuthUser.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole().grantedAuthority())
                .accountNonExpired(user.isAccountNonExpired())
                .accountNonLocked(user.isAccountNonLocked())
                .credentialsNonExpired(user.isCredentialsNonExpired())
                .enabled(user.isEnabled())
                .build();
        return Optional.ofNullable(authUser);
    }
}
