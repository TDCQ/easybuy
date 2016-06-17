package com.easybuy.action.productCategory;

import com.easybuy.model.ProductCategory;
import com.easybuy.service.ProductCategoryService;
import com.easybuy.service.impl.ProductCategoryServiceImpl;
import com.easybuy.util.FreeMarker;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/6/15.
 */
@WebServlet(name = "ProductCategoryManageServlet", urlPatterns = {"/manage/productCategory.html"})
public class ProductCategoryManageServlet extends HttpServlet {
    private ProductCategoryService pcs = new ProductCategoryServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String operator = request.getParameter("op");

        if(operator.equals("modify")){
            Integer id = Integer.parseInt(request.getParameter("id"));
            Integer parentId = Integer.parseInt(request.getParameter("parentId"));
            String name = request.getParameter("name");
            ProductCategory productCategory = pcs.getProductCategoryById(id);
            productCategory.setParentId(parentId);
            productCategory.setName(name);
            boolean flag = pcs.updateProductCategory(productCategory);
            if(flag){
                response.sendRedirect("/manage/productCategory.html");
            }
        } else if(operator.equals("add")){
            Integer parentId = Integer.parseInt(request.getParameter("parentId"));
            ProductCategory parentPC = pcs.getProductCategoryById(parentId);
            if(parentPC != null){
                ProductCategory productCategory = new ProductCategory();
                productCategory.setName(request.getParameter("name"));
                productCategory.setParentId(parentId);
                productCategory.setLevel(parentPC.getLevel()+1);
                boolean flag = pcs.addProductCategory(productCategory);
                if(flag){
                    response.sendRedirect("/manage/productCategory.html");
                }
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String operator = request.getParameter("op");

        if(operator==null){
            List<ProductCategory> list = new ArrayList<ProductCategory>();
            list = pcs.getProductCategorys();

            Map<String, Object> root = new HashMap<String, Object>();
            root.put("allProductCategory", list);
            String template = "manage/productClass.ftl";
            templateProcess(template, root, out);
        } else if(operator.equals("modify")) {
            // 处理预修改操作
            Integer id = Integer.parseInt(request.getParameter("id"));
            ProductCategory productCategory = pcs.getProductCategoryById(id);
            Integer level = productCategory.getLevel();
            List<ProductCategory> list = pcs.getProductCategoryByLevel(level-1);

//            System.out.println("=================>" + list.size());
            Map<String, Object> root = new HashMap<>();
            root.put("parentCategory", list);
            root.put("category", productCategory);
            String template="manage/productClass-modify.ftl";
            templateProcess(template, root, out);
        } else if(operator.equals("delete")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            ProductCategory productCategory = pcs.getProductCategoryById(id);
            boolean flag = pcs.delProductCategory(productCategory);
            System.out.println(flag);
            if(flag){
                response.sendRedirect("/manage/productCategory.html");
            }
        } else if(operator.equals("add")){
            int level = Integer.parseInt(request.getParameter("level"));
            List<ProductCategory> list = pcs.getProductCategoryByLevel(level);
            Map<String, Object> root = new HashMap<>();
            root.put("parentCategory", list);
            root.put("level", level);
            String template = "manage/productClass-add.ftl";
            templateProcess(template, root, out);
        }
    }

    protected void templateProcess( String templateName, Map<String, Object> root, PrintWriter out) throws IOException {
        // 获取设置中心
        Configuration cfg = FreeMarker.getConfiguration();
        // 创建数据模型
//        Map<String, Object> root = new HashMap<String, Object>();
//        root.put("allProductCategory", list);
        // 加载模板
        Template template = cfg.getTemplate(templateName);
        // 合并模板与数据
        try {
            template.process(root, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
