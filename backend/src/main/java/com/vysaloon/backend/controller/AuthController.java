package com.vysaloon.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vysaloon.backend.dto.RegisterRequest;
import com.vysaloon.backend.dto.UserResponse;
import com.vysaloon.backend.entity.User;
import com.vysaloon.backend.service.AuthService;
import com.vysaloon.backend.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

        User user = authService.register(request);

UserResponse response = new UserResponse();

response.setId(user.getId());
response.setName(user.getName());
response.setEmail(user.getEmail());
response.setRole(user.getRole());

return ResponseEntity.ok(
        ApiResponse.success("User registered successfully", response)
);
    }
}