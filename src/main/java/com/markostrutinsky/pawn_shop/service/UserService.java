package com.markostrutinsky.pawn_shop.service;

import com.markostrutinsky.pawn_shop.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);

    List<User> getUsers();

    void deleteUser(String email);

    User getUser(String email);
}
