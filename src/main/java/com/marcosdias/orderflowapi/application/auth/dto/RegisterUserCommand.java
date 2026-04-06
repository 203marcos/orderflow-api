package com.marcosdias.orderflowapi.application.auth.dto;

public record RegisterUserCommand(
        String name,
        String email,
        String password
) {
}

