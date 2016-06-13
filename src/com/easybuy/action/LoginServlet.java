package com.easybuy.action;

import com.easybuy.model.User;
import com.easybuy.service.UserService;
import com.easybuy.service.impl.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by lenovo on 2016/6/11.
 */
@WebServlet(name = "LoginServlet.java", urlPatterns = {"/login.html", "/login-result.html"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        UserService userService = new UserServiceImpl();
        String name = request.getParameter("userName");
        String password = request.getParameter("passWord");
        ServletOutputStream out = response.getOutputStream();

        // 验证邮箱与密码
        User user = userService.login(name, password);
        if(user!= null){
            out.println("Login Success");
//            out.close();
            Cookie cookie = new Cookie("user", user.getName());
            cookie.setMaxAge(7 * 24 * 60 * 60);     // 一星期有效
            response.addCookie(cookie);
            Cookie cookie1 = new Cookie("id", Long.toString(user.getId()) );
            cookie.setMaxAge(7 * 24 * 60 * 60);     // 一周有效
            response.addCookie(cookie1);

            request.setAttribute("user", user.getName());
            // 页面转发
//            this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
            // 页面重定向
            response.sendRedirect("/index.html");
        }else{
            out.println("Login Fail");
            this.getServletContext().getRequestDispatcher("/login.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        ServletContext servletContext = this.getServletContext();
        InputStream inputStream = servletContext.getResourceAsStream("/html/login.html");

        byte[] bytes = new byte[1024];
        int len = inputStream.read(bytes);
        while(len!= -1){
            out.write(bytes, 0, len);
            len = inputStream.read(bytes);
        }
        inputStream.close();
//        out.println("hello");
        out.close();
    }
}
