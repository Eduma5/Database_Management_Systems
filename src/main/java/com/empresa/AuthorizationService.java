package com.empresa;

public class AuthorizationService {

    public static boolean isAuthorized(User user, String requiredRole) {
        return user.getRole().equals(requiredRole);
    }

    private User getCurrentUser() {
        // Implement logic to retrieve the current user
        return new User("ADMIN"); // Example user
    }

    public void checkAuthorization() {
        User currentUser = getCurrentUser();
        if (AuthorizationService.isAuthorized(currentUser, "ADMIN")) {
            // Permitir acceso a funcionalidades de administrador
        } else {
            // Denegar acceso
        }
    }
}
