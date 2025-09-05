package com.example.GroupWork_4.controller;

import com.example.GroupWork_4.model.User;
import com.example.GroupWork_4.repository.UserRepository;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // User Registration
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }
}