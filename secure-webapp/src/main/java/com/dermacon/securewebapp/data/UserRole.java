package com.dermacon.securewebapp.data;

import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.Id;

public enum UserRole {
    ROLE_USER("user"), ROLE_ADMIN("admin");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
