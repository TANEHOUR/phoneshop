package com.piseth.java.school.phoneshop.phoneshop.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.piseth.java.school.phoneshop.phoneshop.config.security.PermissionEnum.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
    ADMIN(Set.of(BRAND_READ, BRAND_WRITE, MODEL_WRITE, MODEL_READ)),
    SALE(Set.of(BRAND_READ, MODEL_READ));
    private Set<PermissionEnum> permissions;

    public Set<SimpleGrantedAuthority> grantedAuthority() {
        Set<SimpleGrantedAuthority> grantedAuthorities = this.permissions.stream()
                .map(permissionEnum -> new SimpleGrantedAuthority(permissionEnum.getDescription()))
                .collect(Collectors.toSet());

        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + this.name());
        grantedAuthorities.add(role);
        return grantedAuthorities;
    }

}
