package com.roy.dao.user;

import com.roy.dao.BaseDao;
import com.roy.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserDaoImp implements UserDao{
    public User getLoginUser(Connection connection, String userCode) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        if(connection != null){
            String sql = "select * from smbms_user where userCode=?";
            Object[] params = {userCode};
            try {
                rs = BaseDao.execute(connection,sql,params,rs,ps );
                while(rs.next()){
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserCode(rs.getString("userCode"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserPassword(rs.getString("userPassword"));
                    user.setGender(rs.getInt("gender"));
                    user.setBirthday((Date) rs.getObject("birthday"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setUserRole(rs.getLong("userRole"));
                    user.setCreatedBy(rs.getLong("createdBy"));
                    user.setCreationDate(rs.getTimestamp("creationDate"));
                    user.setModifyBy(rs.getLong("modifyBy"));
                    user.setModifyDate(rs.getTimestamp("modifyDate"));
                }
                BaseDao.closeResource(ps,connection,rs);





            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }



        return user;
    }
}
