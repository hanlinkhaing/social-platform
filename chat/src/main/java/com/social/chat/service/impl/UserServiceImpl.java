package com.social.chat.service.impl;

import com.social.chat.enums.Status;
import com.social.chat.model.User;
import com.social.chat.repository.UserRepository;
import com.social.chat.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository  userRepository;


    @Override
    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }

    @Override
    public void disconnect(User user) {
        User storedUser = userRepository.findById(user.getNickName()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }
    }

    @Override
    public List<User> getConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
