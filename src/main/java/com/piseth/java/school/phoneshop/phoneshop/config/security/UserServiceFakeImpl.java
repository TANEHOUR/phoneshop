package com.piseth.java.school.phoneshop.phoneshop.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceFakeImpl implements UserService{

    private final PasswordEncoder passwordEncoder;
    @Override
    public Optional<AuthUser> findUserByUsername(String username) {
        List<AuthUser> users = List.of(
                new AuthUser("bona", passwordEncoder.encode("bona1234567890"), RoleEnum.SALE.grantedAuthority(), true, true, true, true),
                new AuthUser("seyha", passwordEncoder.encode("seyha1234567890"), RoleEnum.ADMIN.grantedAuthority(), true, true, true, true)
        );
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
