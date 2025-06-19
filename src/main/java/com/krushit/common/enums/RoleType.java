package com.krushit.common.enums;

public enum RoleType {
    ROLE_SUPER_ADMIN(1),
    ROLE_CUSTOMER(2),
    ROLE_DRIVER(3);

    private final int id;

    RoleType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
