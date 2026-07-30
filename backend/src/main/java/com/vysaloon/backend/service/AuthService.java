package com.vysaloon.backend.service;

import com.vysaloon.backend.dto.RegisterRequest;
import com.vysaloon.backend.entity.User;

public interface AuthService {

    User register(RegisterRequest request);
}