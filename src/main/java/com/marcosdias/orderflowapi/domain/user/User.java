package com.marcosdias.orderflowapi.domain.user;

import java.time.Instant;

public class User {

    private final Long id;
    private final String name;
    private final String email;
    private final String passwordHash;
    private final Role role;
    private final Instant createdAt;

    public User(Long id, String name, String email, String passwordHash, Role role, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}


