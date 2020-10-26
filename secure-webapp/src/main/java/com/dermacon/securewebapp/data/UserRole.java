package com.dermacon.securewebapp.data;

import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserRole {

    @Id
    @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
    private long roleId;

    private String userRole;

    public UserRole() {}

    public UserRole(long roleId, String userRole) {
        this.roleId = roleId;
        this.userRole = userRole;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
