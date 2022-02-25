package com.roy.service.user;

import com.roy.pojo.User;

public interface UserService {
    public User login(String userCode,String password);
}
