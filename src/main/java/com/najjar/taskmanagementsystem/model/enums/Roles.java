package com.najjar.taskmanagementsystem.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.najjar.taskmanagementsystem.model.enums.Permissions.*;

@RequiredArgsConstructor
public enum Roles {
    Developer(
//            Collections.emptySet()
            Set.of(
                    MANAGER_READ,
                    MANAGER_CREATE,
                    MANAGER_UPDATE,
                    MANAGER_DELETE
            )
    ),
    Admin(
            Set.of(
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    MANAGER_READ,
                    MANAGER_CREATE,
                    MANAGER_UPDATE,
                    MANAGER_DELETE
            )
    ),
    Manager(
            Set.of(
                    MANAGER_READ,
                    MANAGER_CREATE,
                    MANAGER_UPDATE,
                    MANAGER_DELETE
            )
    );

    @Getter
    private final Set<Permissions> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
