package com.Yassine.dev.api.user.dto;


import com.Yassine.dev.api.security.JwtService;
import com.Yassine.dev.api.user.entity.Role;
import com.Yassine.dev.api.user.entity.User;
import com.Yassine.dev.api.user.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthService(UserRepository users, PasswordEncoder encoder, JwtService jwtService, AuthenticationManager authManager) {
        this.users = users;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    public String register(String email, String rawPassword) {
        if (users.existsByEmail(email)) {
            throw new EntityExistsException("Email already used");
        }

        User u = new User();
        u.setEmail(email);
        u.setPassword(encoder.encode(rawPassword));
        u.setRole(Role.USER); // DEFAULT ROLE USER
        users.save(u);

        return jwtService.generateToken(u.getEmail(), Map.of("role", u.getRole().name()));
    }

    public String login(String email, String password) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        var user = users.findByEmail(email).orElseThrow();

        return jwtService.generateToken(user.getEmail(), Map.of("role", user.getRole().name()));
    }
}
