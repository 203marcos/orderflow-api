package com.marcosdias.orderflowapi.application.auth.usecase.impl;

import com.marcosdias.orderflowapi.application.auth.dto.AuthenticateUserCommand;
import com.marcosdias.orderflowapi.application.auth.dto.AuthenticatedUserView;
import com.marcosdias.orderflowapi.application.auth.exception.InvalidCredentialsException;
import com.marcosdias.orderflowapi.application.auth.port.out.JwtTokenPort;
import com.marcosdias.orderflowapi.application.auth.port.out.PasswordEncoderPort;
import com.marcosdias.orderflowapi.application.auth.port.out.UserRepositoryPort;
import com.marcosdias.orderflowapi.application.auth.usecase.AuthenticateUserUseCase;
import com.marcosdias.orderflowapi.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserService implements AuthenticateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final JwtTokenPort jwtTokenPort;

    public AuthenticateUserService(UserRepositoryPort userRepositoryPort,
                                   PasswordEncoderPort passwordEncoderPort,
                                   JwtTokenPort jwtTokenPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.jwtTokenPort = jwtTokenPort;
    }

    @Override
    public AuthenticatedUserView authenticate(AuthenticateUserCommand command) {
        User user = userRepositoryPort.findByEmail(command.email())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoderPort.matches(command.password(), user.getPasswordHash())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtTokenPort.generateToken(user.getEmail(), user.getRole().getRole());
        return new AuthenticatedUserView(token, user.getEmail(), user.getRole().getRole());
    }
}

