package com.marcosdias.orderflowapi.application.auth.usecase.impl;

import com.marcosdias.orderflowapi.application.auth.dto.CurrentUserView;
import com.marcosdias.orderflowapi.application.auth.exception.UserNotFoundException;
import com.marcosdias.orderflowapi.application.auth.port.out.CurrentUserPort;
import com.marcosdias.orderflowapi.application.auth.port.out.UserRepositoryPort;
import com.marcosdias.orderflowapi.application.auth.usecase.GetCurrentUserUseCase;
import com.marcosdias.orderflowapi.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class GetCurrentUserService implements GetCurrentUserUseCase {

    private final CurrentUserPort currentUserPort;
    private final UserRepositoryPort userRepositoryPort;

    public GetCurrentUserService(CurrentUserPort currentUserPort, UserRepositoryPort userRepositoryPort) {
        this.currentUserPort = currentUserPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public CurrentUserView currentUser() {
        String email = currentUserPort.currentUsername();
        User user = userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return new CurrentUserView(user.getId(), user.getName(), user.getEmail(), user.getRole().getRole());
    }
}

