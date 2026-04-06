package com.marcosdias.orderflowapi.application.auth.usecase;

import com.marcosdias.orderflowapi.application.auth.dto.CurrentUserView;

public interface GetCurrentUserUseCase {

    CurrentUserView currentUser();
}

