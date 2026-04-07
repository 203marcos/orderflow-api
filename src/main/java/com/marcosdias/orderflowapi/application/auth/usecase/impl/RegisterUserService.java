package com.marcosdias.orderflowapi.application.auth.usecase.impl;

import com.marcosdias.orderflowapi.application.auth.dto.RegisterUserCommand;
import com.marcosdias.orderflowapi.application.auth.exception.EmailAlreadyInUseException;
import com.marcosdias.orderflowapi.application.auth.port.out.PasswordEncoderPort;
import com.marcosdias.orderflowapi.application.auth.port.out.UserRepositoryPort;
import com.marcosdias.orderflowapi.application.auth.usecase.RegisterUserUseCase;
import com.marcosdias.orderflowapi.domain.user.Role;
import com.marcosdias.orderflowapi.domain.user.User;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public RegisterUserService(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public void register(RegisterUserCommand command) {
        if (userRepositoryPort.existsByEmail(command.email())) {
            throw new EmailAlreadyInUseException("Email already in use");
        }

        User user = new User(
                null,
                command.name(),
                command.email(),
                passwordEncoderPort.encode(command.password()),
                Role.USER,
                Instant.now()
        );

        userRepositoryPort.save(user);
    }
}

