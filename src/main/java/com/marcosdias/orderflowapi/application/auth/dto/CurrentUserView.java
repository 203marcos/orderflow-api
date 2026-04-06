package com.marcosdias.orderflowapi.application.auth.dto;

public record CurrentUserView(
        Long id,
        String name,
        String email,
        String role
) {
}

