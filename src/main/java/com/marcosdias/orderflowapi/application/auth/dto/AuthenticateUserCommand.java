package com.marcosdias.orderflowapi.application.auth.dto;

public record AuthenticateUserCommand(
        String email,
        String password
) {
}

