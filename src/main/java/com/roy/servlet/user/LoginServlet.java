package com.roy.servlet.user;

import com.roy.pojo.User;
import com.roy.service.user.UserServiceImp;
import com.roy.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("loginServlet_start");
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        UserServiceImp userServiceImp = new UserServiceImp();
        User user = userServiceImp.login(userCode, userPassword);
        if(user != null){
            req.getSession().setAttribute(Constant.USER_SESSION,user);
            resp.sendRedirect("jsp/frame.jsp");
        }else{
            req.setAttribute("error", "用户名密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
