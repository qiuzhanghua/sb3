package com.example.sb3.service;

import com.example.sb3.domain.User;
import com.example.sb3.domain.UserRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Transactional
    public void save(User user) {
        this.userRepository.save(user);
    }
}
