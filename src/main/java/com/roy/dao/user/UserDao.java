package com.roy.dao.user;

import com.roy.pojo.User;

import java.sql.Connection;

public interface UserDao {
    //获取用户信息
    public User getLoginUser(Connection connection,String userCode);
}
