package com.easybuy.action;

import com.easybuy.model.User;
import com.easybuy.service.UserService;
import com.easybuy.service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by lenovo on 2016/6/12.
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register.html", "/reg-result.html"})
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("passWord");

        User user = userService.getUserByEmail(email);
        if(user==null){
            user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            boolean flag = userService.addUser(user);
            if(flag){
                response.sendRedirect("/index.html");
            }else {
                out.println(name + "\t" + email + "\t" + password);
                out.close();
            }
        }else{
            out.println("duplicate email ");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset='UTF-8'");
        OutputStream out = response.getOutputStream();
        ServletContext servletContext = this.getServletContext();
        InputStream inputStream = servletContext.getResourceAsStream("/html/register.html");

        byte[] array = new byte[1024];
        int len = inputStream.read(array);
        while(len!=-1){
            out.write(array, 0, len);
            len = inputStream.read(array);
        }
        inputStream.close();
        out.close();
    }
}
