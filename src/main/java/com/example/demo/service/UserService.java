package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();

    User getLastAuthoredUser();

    Boolean isUserIdAndPasswordValid(String validatedUserId, String text);

}
