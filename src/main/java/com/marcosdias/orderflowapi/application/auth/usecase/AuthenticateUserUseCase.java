package com.marcosdias.orderflowapi.application.auth.usecase;

import com.marcosdias.orderflowapi.application.auth.dto.AuthenticateUserCommand;
import com.marcosdias.orderflowapi.application.auth.dto.AuthenticatedUserView;

public interface AuthenticateUserUseCase {

    AuthenticatedUserView authenticate(AuthenticateUserCommand command);
}

