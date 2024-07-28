package com.social.chat.service;

import com.social.chat.model.User;

import java.util.List;

public interface IUserService {
    void saveUser(User user);

    void disconnect(User user);

    List<User> getConnectedUsers();
}
