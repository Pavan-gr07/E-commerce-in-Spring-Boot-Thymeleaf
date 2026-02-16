package com.example.ecommerce.service;

import org.springframework.stereotype.Service;

import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRespository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRespository userRepository;

    public UserServiceImpl(UserRespository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        user.setRole("CUSTOMER");
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword()); // In production, hash the password
        userRepository.save(user);
    }

    @Override
    public User loginUser(User user) {
        if (user == null || !user.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        return user;
    }

}