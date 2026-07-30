package com.vysaloon.backend.service;

import com.vysaloon.backend.dto.RegisterRequest;
import com.vysaloon.backend.entity.User;
import com.vysaloon.backend.dto.LoginRequest;
import com.vysaloon.backend.dto.LoginResponse;



public interface AuthService {

    User register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}