package com.easybuy.action.userManager;

import com.easybuy.model.Gender;
import com.easybuy.model.User;
import com.easybuy.model.UserStatus;
import com.easybuy.service.UserService;
import com.easybuy.service.impl.UserServiceImpl;
import com.easybuy.util.FreeMarker;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2016/6/15.
 */
@WebServlet(name = "AddUserServlet", urlPatterns = {"/manage/addUser.html"})
public class AddUserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String strGender = request.getParameter("gender");
        String strBirthday = request.getParameter("birthday");
        String identityCode = request.getParameter("identityCode");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        String strStatus = request.getParameter("status");

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        for(Gender gender : Gender.values()){
            if(strGender.equals(gender.getGender())){
                user.setGender(gender);
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            user.setBirthday(sdf.parse(strBirthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setIdentityCode(identityCode);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setAddress(address);
        for(UserStatus status: UserStatus.values()){
            if(strStatus.equals(status.getRole())){
                user.setStatus(status);
            }
        }

        boolean flag = userService.addUser(user);
        if(flag){
            response.sendRedirect("/manage/getUsers.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        // 获取控制中心
        Configuration cfg = FreeMarker.getConfiguration();
        // 创建数据模型
        Map<String, Object> root = new HashMap<>();
        root.put("user", user);
        root.put("genders", Gender.values());
        root.put("userStatus", UserStatus.values());
        // 获取模板
        Template template = cfg.getTemplate("manage/preAddUser.ftl");
        // 合并模板与数据
        try {
            template.process(root, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
