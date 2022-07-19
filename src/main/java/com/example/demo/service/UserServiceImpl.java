package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UserServiceImpl implements UserService {
    public static final List<User> DEMO_USER_LIST;

    static {

        Faker faker = new Faker(new Locale("ru-RU"));
        DEMO_USER_LIST = IntStream.range(1, 101)
                .mapToObj(e -> new User(String.format("%d", e), faker.name().fullName())).collect(Collectors.toList());
    }

    @Override
    public List<User> getUserList() {
        return DEMO_USER_LIST;
    }

    @Override
    public User getLastAuthoredUser() {
        return DEMO_USER_LIST.get(23);
    }

    @Override
    public Boolean isUserIdAndPasswordValid(String validatedUserId, String text) {
        return text.equals("123");
    }
}
