package com.Yassine.dev.api.user.dto;

import com.Yassine.dev.api.security.JwtService;
import com.Yassine.dev.api.user.entity.Role;
import com.Yassine.dev.api.user.entity.User;
import com.Yassine.dev.api.user.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(UUID.randomUUID());
        testUser.setEmail("test@example.com");
        testUser.setPassword("encodedPassword");
        testUser.setPhoneNumber("+1234567890");
        testUser.setRole(Role.USER);
    }

    @Test
    void register_ShouldReturnJwtToken_WhenEmailNotExists() {
        // Given
        String email = "new@example.com";
        String password = "password123";
        String phoneNumber = "+1234567890";
        String encodedPassword = "encodedPassword";
        String token = "jwt-token";

        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(jwtService.generateToken(anyString(), any(Map.class))).thenReturn(token);

        // When
        String result = authService.register(email, password, phoneNumber);

        // Then
        assertThat(result).isEqualTo(token);
        verify(userRepository).existsByEmail(email);
        verify(passwordEncoder).encode(password);
        verify(userRepository).save(any(User.class));
        verify(jwtService).generateToken(eq(email), any(Map.class));
    }

    @Test
    void register_ShouldThrowException_WhenEmailAlreadyExists() {
        // Given
        String email = "existing@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        // When & Then
        assertThatThrownBy(() -> authService.register(email, "password", "+1234567890"))
                .isInstanceOf(EntityExistsException.class)
                .hasMessage("Email already used");
        verify(userRepository).existsByEmail(email);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void login_ShouldReturnJwtToken_WhenCredentialsValid() {
        // Given
        String email = "test@example.com";
        String password = "password123";
        String token = "jwt-token";

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(testUser));
        when(jwtService.generateToken(anyString(), any(Map.class))).thenReturn(token);

        // When
        String result = authService.login(email, password);

        // Then
        assertThat(result).isEqualTo(token);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByEmail(email);
        verify(jwtService).generateToken(eq(email), any(Map.class));
    }

    @Test
    void login_ShouldThrowException_WhenUserNotFound() {
        // Given
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> authService.login(email, "password"))
                .isInstanceOf(RuntimeException.class);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }
}