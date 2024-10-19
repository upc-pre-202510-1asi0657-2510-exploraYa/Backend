// src/main/java/com/upc/aventurape/platform/iam/infrastructure/security/SecurityUtils.java
package com.upc.aventurape.platform.iam.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            // Asumiendo que el nombre de usuario es el ID del usuario
            return Long.parseLong(userDetails.getUsername());
        }
        return null;
    }
}