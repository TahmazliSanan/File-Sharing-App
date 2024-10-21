package org.pronet.app.auth;

public class AuthRequestMatcher {
    public static final String[] NON_AUTH_ROUTES = {
            "/api/v1/auth/login"
    };

    public static final String[] ADMIN_AUTH_ROUTES = {
            "/api/v1/user/create",
            "/api/v1/user/all",
            "/api/v1/file/{id}",
            "/api/v1/file/all",
            "/api/v1/file/delete/{id}"
    };

    public static final String[] USER_AUTH_ROUTES = {
            "/api/v1/file/create",
            "/api/v1/file/user/{userId}"
    };

    public static final String[] ADMIN_AND_USER_AUTH_ROUTES = {
            "/api/v1/user/{id}",
            "/api/v1/user/update/{id}",
            "/api/v1/user/delete/{id}"
    };
}
