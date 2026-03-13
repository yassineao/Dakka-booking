package com.Yassine.dev.api.user.controller;

import com.Yassine.dev.api.user.dto.AuthRequest;
import com.Yassine.dev.api.user.dto.AuthResponse;
import com.Yassine.dev.api.user.dto.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody AuthRequest req) {
        String token = service.register(req.getEmail(), req.getPassword());
        return new AuthResponse(token);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest req) {
        String token = service.login(req.getEmail(), req.getPassword());
        return new AuthResponse(token);
    }
}
