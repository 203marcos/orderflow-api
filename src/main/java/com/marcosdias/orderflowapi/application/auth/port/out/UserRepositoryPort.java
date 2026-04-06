package com.marcosdias.orderflowapi.application.auth.port.out;

import com.marcosdias.orderflowapi.domain.user.User;
import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    User save(User user);
}

