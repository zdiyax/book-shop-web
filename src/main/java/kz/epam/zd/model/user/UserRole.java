package kz.epam.zd.model.user;

import kz.epam.zd.model.Entity;

public class UserRole extends Entity {
    private RoleType roleType;

    public UserRole(RoleType roleType) {
        this.roleType = roleType;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return roleType.toString();
    }
}
