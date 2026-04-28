package com.library.service;
import com.library.security.JwtUtil;

import com.library.dto.LoginDto;
import com.library.dto.RegisterDto;
import com.library.model.User;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // 🟢 REGISTER
    public User register(RegisterDto dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole("USER");

        return userRepository.save(user);
    }

    // 🔐 LOGIN

    public String login(LoginDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 🔥 generate token
        return JwtUtil.generateToken(user.getEmail(), user.getRole());    }
}
