package com.vysaloon.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "Application is Healthy ✅";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to VY Saloon Backend 🚀";
    }
}