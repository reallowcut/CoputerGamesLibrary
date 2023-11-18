package com.aply.application.sptingapplication.model;

public enum Permissions {
    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_WRITE("developers:write");

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
