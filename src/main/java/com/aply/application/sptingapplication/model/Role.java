package com.aply.application.sptingapplication.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permissions.DEVELOPERS_READ)),
    ADMIN(Set.of(Permissions.DEVELOPERS_READ, Permissions.DEVELOPERS_WRITE));


    private final Set<Permissions> permissionsSet;

    Role(Set<Permissions> permissionsSet) {
        this.permissionsSet = permissionsSet;
    }

    public Set<Permissions> getPermissionsSet() {
        return permissionsSet;
    }

    public Set<SimpleGrantedAuthority> getAuthority() {
        return getPermissionsSet().stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission())).collect(Collectors.toSet());
    }
}
