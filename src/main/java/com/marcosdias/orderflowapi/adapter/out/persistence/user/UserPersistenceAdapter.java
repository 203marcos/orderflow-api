package com.marcosdias.orderflowapi.adapter.out.persistence.user;

import com.marcosdias.orderflowapi.adapter.out.persistence.user.entity.UserEntity;
import com.marcosdias.orderflowapi.adapter.out.persistence.user.mapper.UserPersistenceMapper;
import com.marcosdias.orderflowapi.adapter.out.persistence.user.repository.UserJpaRepository;
import com.marcosdias.orderflowapi.application.auth.port.out.UserRepositoryPort;
import com.marcosdias.orderflowapi.domain.user.User;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;

    public UserPersistenceAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(UserPersistenceMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        UserEntity savedUser = userJpaRepository.save(UserPersistenceMapper.toEntity(user));
        return UserPersistenceMapper.toDomain(savedUser);
    }
}

