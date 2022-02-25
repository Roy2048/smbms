package com.roy.service.user;

import com.roy.dao.BaseDao;
import com.roy.dao.user.UserDao;
import com.roy.dao.user.UserDaoImp;
import com.roy.pojo.User;
import org.junit.Test;

import java.sql.Connection;

public class UserServiceImp implements UserService{
    private UserDao userDao;

    public UserServiceImp() {
         userDao = new UserDaoImp();
    }


    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;

        connection = BaseDao.getConnection();
        user = userDao.getLoginUser(connection, userCode);


        BaseDao.closeResource(null, connection,null);


        return user;
    }
    @Test
    public void test(){
        UserServiceImp userServiceImp = new UserServiceImp();
        User admin = userServiceImp.login("admin", "1234567");
        System.out.println(admin.getUserPassword());
    }
}
