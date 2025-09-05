package com.example.GroupWork_4.service;

import com.example.GroupWork_4.model.User;
import com.example.GroupWork_4.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private  final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  User validateUser(String username){
        User user = userRepository.findByUsername(username);

        if (user == null){
            throw new RuntimeException( "Invalide user name: " +username);
        }
        return user;
    }
}
