package com.easybuy.action;

import com.easybuy.model.User;
import com.easybuy.model.UserStatus;
import com.easybuy.service.UserService;
import com.easybuy.service.impl.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2016/6/11.
 */
@WebServlet(name = "LoginServlet.java", urlPatterns = {"/login.html", "/login-result.html"})
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        UserService userService = new UserServiceImpl();
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("passWord");
        ServletOutputStream out = response.getOutputStream();

        // 验证邮箱与密码
        User user = userService.login(loginName, password);
        if(user!= null){
            out.println("Login Success");

            // “记住登录”功能
            String remember = request.getParameter("remember");
            if(remember!=null){
                Cookie pwdCookie = new Cookie("passWord", password);
                pwdCookie.setMaxAge(365*24*60*60);  // 一年有效
                response.addCookie(pwdCookie);
            }

            Cookie nameCookie = new Cookie("user", user.getName());
            nameCookie.setMaxAge(7 * 24 * 60 * 60);     // 一星期有效
            response.addCookie(nameCookie);
            Cookie loginNameCookie = new Cookie("loginName", loginName);
            loginNameCookie.setMaxAge(7 * 24 * 60 * 60);     // 一周有效
            response.addCookie(loginNameCookie);
            Cookie idCookie = new Cookie("id", Long.toString(user.getId()) );
            idCookie.setMaxAge(7 * 24 * 60 * 60);     // 一周有效
            response.addCookie(idCookie);

            request.setAttribute("user", user.getName());
            // 页面转发
//            this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
            // 页面重定向
            response.sendRedirect("/index.html");
        }else{
            out.println("Login Fail");
            response.sendRedirect("/login.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置自动登陆cookie，根据cookie中的用户名，密码登陆
        UserService userService = new UserServiceImpl();
        getCookies(request, response);
        HttpSession session = request.getSession();
        Map<String, String> cookiesMap = (Map<String, String>)session.getAttribute("cookiesMap");
        String loginName = cookiesMap.get("loginName");
        String password = cookiesMap.get("passWord");
        User user=new User();
        if(loginName!=null && password!=null){
            user = userService.login(loginName, password);
        }
        if(user.getId() != null){
            log(user.getEmail() + user.getPassword() + "login Success");

            if(user.getStatus()== UserStatus.ADMINISTRATOR){
                // 如果是管理员则转向至后台首页
                response.sendRedirect("/manage/index.html");
            }else if(user.getStatus() == UserStatus.NORMAL) {
                // 如果是普通用户则重定向到前台首页
                response.sendRedirect("/index.html");
            }

        }

        // 未设置自动登录，则输出登录表格供用户登录
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
        out.close();
    }

    protected void getCookies(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> cookiesMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(int i=0; i<cookies.length; i++){
                String key = cookies[i].getName();
                String value = cookies[i].getValue();
                cookiesMap.put(key, value);
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("cookiesMap", cookiesMap);
    }

}
