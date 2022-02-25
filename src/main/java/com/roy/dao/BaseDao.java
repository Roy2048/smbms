package com.roy.dao;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//操作数据库的公共类
public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        // 从db.properties获取数据库连接的参数
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            driver = properties.getProperty("driver");

            url = properties.getProperty("url");
            System.out.println(url);
            username = properties.getProperty("username");
            password = properties.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    //连接数据库
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
    //查询公共类：(为什么要提取resultset和preparedStatement）
    public static ResultSet execute(Connection connection,String sql,Object[] params,ResultSet resultSet,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i+1, params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
    //增删改公共类
    public static int execute(Connection connection,String sql,Object[] params,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i+1, params[i]);
        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }
    //释放连接
    public static boolean closeResource(PreparedStatement preparedStatement,Connection connection,ResultSet resultSet){
        boolean flag = true;
        if (resultSet != null){
            try {
                resultSet.close();
                resultSet = null;
                System.gc();
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }
        if (connection != null){
            try {
                connection.close();
                connection = null;
                System.gc();
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }
        if (preparedStatement != null){
            try {
                preparedStatement.close();
                preparedStatement = null;
                System.gc();
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }
        return flag;
    }
}
