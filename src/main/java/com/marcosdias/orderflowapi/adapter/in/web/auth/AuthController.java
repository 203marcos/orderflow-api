package com.marcosdias.orderflowapi.adapter.in.web.auth;

import com.marcosdias.orderflowapi.application.auth.dto.AuthenticateUserCommand;
import com.marcosdias.orderflowapi.application.auth.dto.AuthenticatedUserView;
import com.marcosdias.orderflowapi.application.auth.dto.CurrentUserView;
import com.marcosdias.orderflowapi.application.auth.dto.RegisterUserCommand;
import com.marcosdias.orderflowapi.application.auth.usecase.AuthenticateUserUseCase;
import com.marcosdias.orderflowapi.application.auth.usecase.GetCurrentUserUseCase;
import com.marcosdias.orderflowapi.application.auth.usecase.RegisterUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase,
                          AuthenticateUserUseCase authenticateUserUseCase,
                          GetCurrentUserUseCase getCurrentUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.getCurrentUserUseCase = getCurrentUserUseCase;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Void> register(@RequestBody RegisterUserCommand command) {
        registerUserUseCase.register(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticatedUserView> login(@RequestBody AuthenticateUserCommand command) {
        return ResponseEntity.ok(authenticateUserUseCase.authenticate(command));
    }

    @GetMapping("/users/me")
    public ResponseEntity<CurrentUserView> me() {
        return ResponseEntity.ok(getCurrentUserUseCase.currentUser());
    }
}

