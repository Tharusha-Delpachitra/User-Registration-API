package org.example.user_api.model;

public class UserRoles {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public static boolean isValidRole(String role) {
        return ADMIN.equals(role) || USER.equals(role);
    }
}
