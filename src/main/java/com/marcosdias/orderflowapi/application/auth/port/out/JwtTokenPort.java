package com.marcosdias.orderflowapi.application.auth.port.out;

public interface JwtTokenPort {

    String generateToken(String subject, String role);
}

