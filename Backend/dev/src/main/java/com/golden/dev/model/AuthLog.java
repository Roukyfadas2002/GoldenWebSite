package com.golden.dev.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auth_logs")
public class AuthLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String status; // SUCCESS, FAILURE, USER_NOT_FOUND

    @Column(nullable = false)
    private LocalDateTime timestamp;

    private String message; // Message complémentaire

    public AuthLog() {
        this.timestamp = LocalDateTime.now();
    }

    public AuthLog(String email, String status, String message) {
        this.email = email;
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
