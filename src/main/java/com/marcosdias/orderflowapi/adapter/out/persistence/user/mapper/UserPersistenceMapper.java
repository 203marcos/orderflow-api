package com.marcosdias.orderflowapi.adapter.out.persistence.user.mapper;

import com.marcosdias.orderflowapi.adapter.out.persistence.user.entity.UserEntity;
import com.marcosdias.orderflowapi.domain.user.User;

public final class UserPersistenceMapper {

    private UserPersistenceMapper() {
    }

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.getRole(),
                entity.getCreatedAt()
        );
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}

