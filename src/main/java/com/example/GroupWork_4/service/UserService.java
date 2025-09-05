package com.example.GroupWork_4.service;

import com.example.GroupWork_4.model.User;
import com.example.GroupWork_4.repository.UserRepository;
import jdk.internal.jrtfs.JrtFileSystemProvider;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User register(User user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }
        return userRepo.saveAndFlush(user);
    }

    public Optional<User> validateUsername(String username) {
        return userRepo.findByUsername(username);
    }


    public User getUserByUsername(String username) {
        return null;
    }


}
