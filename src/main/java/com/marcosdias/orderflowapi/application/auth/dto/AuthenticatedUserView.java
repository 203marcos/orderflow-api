package com.marcosdias.orderflowapi.application.auth.dto;

public record AuthenticatedUserView(
        String token,
        String email,
        String role
) {
}

