package com.vysaloon.backend.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vysaloon.backend.dto.RegisterRequest;
import com.vysaloon.backend.entity.User;
import com.vysaloon.backend.repository.UserRepository;
import com.vysaloon.backend.security.JwtService;
import com.vysaloon.backend.service.AuthService;
import com.vysaloon.backend.dto.LoginRequest;
import com.vysaloon.backend.dto.LoginResponse;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,
    PasswordEncoder passwordEncoder,
    JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
}

    @Override
public User register(RegisterRequest request) {

    if (userRepository.existsByEmail(request.getEmail())) {
        throw new RuntimeException("Email already exists");
    }

    User user = new User();

    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(request.getRole());

    return userRepository.save(user);

    
}
@Override
public LoginResponse login(LoginRequest request) {

    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid email or password");
    }

    String token = jwtService.generateToken(user);

    return new LoginResponse(token);
}
}
