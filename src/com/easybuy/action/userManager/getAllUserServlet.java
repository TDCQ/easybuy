package com.easybuy.action.userManager;

import com.easybuy.model.User;
import com.easybuy.service.UserService;
import com.easybuy.service.impl.UserServiceImpl;
import com.easybuy.util.FreeMarker;
import com.easybuy.util.Range;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/6/13.
 */
@WebServlet(name = "getAllUserServlet", urlPatterns = {"/manage/getUsers.html"})
public class getAllUserServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String strPageIndex = request.getParameter("pageIndex");
        int pageIndex = strPageIndex != null? Integer.parseInt(strPageIndex)-1 : 0;   // 第几页
        String strPageSize = request.getParameter("pageSize");
        int pageSize = strPageSize != null ? Integer.parseInt(strPageSize) : 3;        // 每页的记录数

        List<User> userList = userService.getUsersPage(pageSize, pageIndex);

        // 创建设置中心
        Configuration cfg = FreeMarker.getConfiguration();
        // 创建数据模型
        Map<String, Object> root = new HashMap<>();
        root.put("userList", userList);

        int totalRows = userService.getUserNum();
        int[] pages = new int[(totalRows+pageSize-1)/pageSize];
        Range.range(pages);
        root.put("pages", pages);
        root.put("pageIndex", pageIndex);
        root.put("pageSize", pageSize);
        // 获取模板
        Template template = cfg.getTemplate("manage/user-getUsers.ftl");
        // 合并模板与数据
        try {
            template.process(root, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
