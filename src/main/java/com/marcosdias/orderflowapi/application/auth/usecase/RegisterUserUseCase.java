package com.marcosdias.orderflowapi.application.auth.usecase;

import com.marcosdias.orderflowapi.application.auth.dto.RegisterUserCommand;

public interface RegisterUserUseCase {

    void register(RegisterUserCommand command);
}

