package com.js.lib.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "registration_date")
    private Instant registrationDate;

    public User(UUID id, String username, String email, String passwordHash, Instant registrationDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.registrationDate = registrationDate;
    }
}
