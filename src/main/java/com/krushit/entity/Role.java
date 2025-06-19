package com.krushit.entity;

import com.krushit.common.enums.RoleType;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    private int roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false, unique = true)
    private RoleType role;

    public Role(int roleId, RoleType role) {
        this.roleId = roleId;
        this.role = role;
    }

    public Role() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public RoleType getRoleType() {
        return role;
    }

    public void setRoleType(RoleType roleName) {
        this.role = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + role + '\'' +
                '}';
    }
}
