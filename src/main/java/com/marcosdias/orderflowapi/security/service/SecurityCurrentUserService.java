package com.marcosdias.orderflowapi.security.service;

import com.marcosdias.orderflowapi.application.auth.port.out.CurrentUserPort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityCurrentUserService implements CurrentUserPort {

    @Override
    public String currentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new IllegalStateException("No authenticated user");
        }
        return authentication.getName();
    }
}

