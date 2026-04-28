package com.library.controller;

import com.library.dto.LoginDto;
import com.library.dto.RegisterDto;
import com.library.model.User;
import com.library.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // 🟢 REGISTER
    @PostMapping("/register")
    public User register(@RequestBody RegisterDto dto) {
        return authService.register(dto);
    }

    // 🔐 LOGIN
    @PostMapping("/login")
    public String login(@RequestBody LoginDto dto) {
        return authService.login(dto);
    }
}