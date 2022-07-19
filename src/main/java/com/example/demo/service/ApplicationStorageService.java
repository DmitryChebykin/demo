package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ApplicationStorageService {
    private final AtomicReference<String> validatedUserId = new AtomicReference<>();

    private final AtomicReference<String> authenticatedUserId = new AtomicReference<>();

    public Optional<String> getValidatedUserId() {
        return Optional.ofNullable(validatedUserId.get());
    }

    public void setValidatedUserId(String id) {
        validatedUserId.set(id);
    }

    public void setAuthenticatedUserId(String id) {
        authenticatedUserId.set(id);
    }
}
