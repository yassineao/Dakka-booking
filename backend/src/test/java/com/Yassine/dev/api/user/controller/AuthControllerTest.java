package com.Yassine.dev.api.user.controller;

import com.Yassine.dev.api.user.dto.AuthRequest;
import com.Yassine.dev.api.user.dto.AuthResponse;
import com.Yassine.dev.api.user.dto.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService service;

    @InjectMocks
    private AuthController controller;

    @Test
    void register_ShouldReturnAuthResponse() {
        // Given
        AuthRequest request = new AuthRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");
        request.setPhoneNumber("+1234567890");

        String token = "jwt-token";
        when(service.register(anyString(), anyString(), anyString())).thenReturn(token);

        // When
        AuthResponse result = controller.register(request);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getToken()).isEqualTo(token);
    }

    @Test
    void login_ShouldReturnAuthResponse() {
        // Given
        AuthRequest request = new AuthRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");

        String token = "jwt-token";
        when(service.login(anyString(), anyString())).thenReturn(token);

        // When
        AuthResponse result = controller.login(request);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getToken()).isEqualTo(token);
    }
}