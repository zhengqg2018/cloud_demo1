package com.zqg.provider_one.service;

import com.zqg.provider_one.entity.User;

public interface UserService {
    void saveUser(User user);

    User findUserById(String id);
}
