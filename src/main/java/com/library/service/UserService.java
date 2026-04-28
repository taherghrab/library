package com.library.service;

import com.library.model.User;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User updateRole(Long id, String role) {
        User user = userRepository.findById(id).orElseThrow();
        user.setRole(role);
        return userRepository.save(user);
    }
}