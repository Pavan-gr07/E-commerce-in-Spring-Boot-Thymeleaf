package com.example.ecommerce.service;

import com.example.ecommerce.model.User;

public interface UserService {

    void registerUser(User user);

    User loginUser(User user);

}
