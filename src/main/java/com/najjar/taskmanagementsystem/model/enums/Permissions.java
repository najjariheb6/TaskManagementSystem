package com.najjar.taskmanagementsystem.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permissions {
    DEVELOPER_READ("developer:read"),
    DEVELOPER_UPDATE("developer:update"),
    DEVELOPER_CREATE("developer:create"),
    DEVELOPER_DELETE("developer:delete"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("manager:read"),
    MANAGER_UPDATE("manager:update"),
    MANAGER_CREATE("manager:create"),
    MANAGER_DELETE("manager:delete");

    @Getter
    private final String permission;
}
