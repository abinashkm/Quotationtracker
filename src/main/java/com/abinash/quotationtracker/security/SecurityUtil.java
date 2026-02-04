package com.abinash.quotationtracker.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getName() == null) {
            return null;
        }

        return Long.parseLong(auth.getName());
    }

    public static String getCurrentUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getAuthorities().isEmpty()) {
            return null;
        }

        return auth.getAuthorities()
                .iterator()
                .next()
                .getAuthority();
    }
}
