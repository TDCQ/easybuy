package com.easybuy.dao.impl;

import com.easybuy.dao.ProductCategoryDao;
import com.easybuy.model.Product;
import com.easybuy.model.ProductCategory;
import com.easybuy.util.DButils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/6/15.
 */
public class ProductCategoryDaoImpl implements ProductCategoryDao {
    /**
     * 增加商品类别
     * @param   //增加商品分类
     * @return
     */
    @Override
    public boolean addProductCategory(ProductCategory productCategory) {
        DButils dButils = new DButils();
        String name = productCategory.getName();
        Integer parentId = productCategory.getParentId();
        Integer level = productCategory.getLevel();
        String sql = "INSERT INTO productCategory(name, parentId) VALUES ( ?, ?)";
        int affectedRows = 0;
        if(level!=null){
            sql = "INSERT INTO productCategory( name, parentId, level) VALUES (?, ?, ?)";
            affectedRows = dButils.executeUpdate(sql, name, parentId, level);
        }else{
            affectedRows = dButils.executeUpdate(sql, name, parentId);
        }

        return affectedRows > 0;
    }

    /**
     * 删除产品目录，如果指定的是一个父目录，则会删除父目录的所有子目录
     * @param productCategory
     * @return
     */
    @Override
    public boolean delProductCategory(ProductCategory productCategory) {
        DButils dButils = new DButils();
        boolean flag = true;
        Integer id = productCategory.getId();
        // 找到并删除子分类
        String sql = "SELECT * FROM productCategory WHERE parentId=?";
        ResultSet rs = dButils.executeQuery(sql, id);
        try {
            boolean delSubFlag = true;
            while(rs.next()){
                ProductCategory pc = new ProductCategory();
                boolean flagN = updateProductCategoryFromRS(rs, pc);
                if(flagN){
                    delSubFlag = delProductCategory(pc) && delSubFlag;
                } else {
                    return false;   // 没有成功创建实例，则无法删除，则删除失败。
                }
            }
            flag = delSubFlag;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 删除自身
        String delSelf = "DELETE FROM productCategory WHERE id=?";
        int affectedRows = dButils.executeUpdate(delSelf, id);
        return (affectedRows > 0) && flag; // 如果有任意一个子分类删除失败，则删除失败，但是已经删除的却不能恢复了
    }

    public boolean delProductCategoryById(Integer id){
        DButils dButils = new DButils();
        boolean flag = true;
        // 找到并删除子分类
        String sql = "SELECT * FROM productCategory WHERE parentId=?";
        ResultSet rs = dButils.executeQuery(sql, id);
        try {
            boolean delSubFlag = true;
            Integer subId = -1;
            while(rs.next()){
                subId = rs.getInt("id");
                delSubFlag = delProductCategoryById(subId) && delSubFlag;
            }
            flag = delSubFlag;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 删除自身
        String delSelf = "DELETE FROM productCategory WHERE id=?";
        int affectedRows = dButils.executeUpdate(delSelf, id);
        return (affectedRows > 0) && flag;
    }
    /**
     * 更新产品目录，目录重命名，修改所属分类
     * @param productCategory
     * @return
     */
    @Override
    public boolean updateProductCategory(ProductCategory productCategory) {
        DButils dButils = new DButils();
        Integer id = productCategory.getId();
        String name = productCategory.getName();
        Integer parentId = productCategory.getParentId();
//        Integer level = productCategory.getLevel();
//
//        ProductCategory old = getProductCategoryById(id);
//        if(old!=null){
//            old.setId(id);
//        }
        String sql = "UPDATE productCategory SET name=?, parentId=? WHERE id=?";
        int affectedRows = dButils.executeUpdate(sql, name, parentId, id);
        return affectedRows > 0;
    }

    @Override
    public ProductCategory getProductCategoryById(Integer id) {
        DButils dButils = new DButils();
        String sql = "SELECT * FROM productCategory WHERE id=?";
        ResultSet rs = dButils.executeQuery(sql, id);
        try {
            if(rs.next()){
                ProductCategory pc = new ProductCategory();
                boolean flag = updateProductCategoryFromRS(rs, pc);
                if(flag){
                    return pc;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<ProductCategory> getProductCategorys() {
        DButils dButils = new DButils();
        //language=MySQL
        String sql = "SELECT * FROM productCategory LIMIT 100";
        ResultSet rs = dButils.executeQuery(sql);
        List<ProductCategory> list = new ArrayList<ProductCategory>();
        try {
            while(rs.next()){
                ProductCategory pc = new ProductCategory();
                updateProductCategoryFromRS(rs, pc);
                if(pc!=null){
                    list.add(pc);
                }else{
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取指定产品分类的全分类
     * @param productCategory       // 产品分类
     * @return
     */
    @Override
    public List<ProductCategory> getProductCategoryFromRoot(ProductCategory productCategory) {
        List<ProductCategory> list = new ArrayList<ProductCategory>();
        Integer level = productCategory.getLevel();
        while(level > 0){
            list.add(productCategory);
            productCategory = getProductCategoryById(productCategory.getParentId());
            if(productCategory==null){
                return null;
            }
            level = productCategory.getLevel();
        }
        return list;
    }

    /**
     * 查询同一级产品分类的所有分类
     * @param level     // 指定的分类等级
     * @return
     */
    @Override
    public List<ProductCategory> getProductCategoryByLevel(Integer level) {
        DButils dButils = new DButils();
        List<ProductCategory> list = new ArrayList<ProductCategory>();
        String sql = "select * FROM productcategory WHERE level=?";
        ResultSet rs = dButils.executeQuery(sql, level);
        try {
            while(rs.next()) {
                ProductCategory productCategory = new ProductCategory();
                boolean flag =  updateProductCategoryFromRS(rs, productCategory);
                if(flag){
                    list.add(productCategory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

//    /**
//     * 从查询结果中组装出“产品分类”对象
//     * @param rs    // 查询结果集
//     * @return
//     */
//    protected ProductCategory getProductCategoryFromRS(ResultSet rs){
//        ProductCategory productCategory= null;
//        try {
//            ProductCategory pc = new ProductCategory();
//            int id = rs.getInt("id");
//            String name = rs.getString("name");
//            int parentId = rs.getInt("parentId");
//            int level = rs.getInt("level");
//            pc.setId(id);
//            pc.setName(name);
//            pc.setParentId(parentId);
//            pc.setLevel(level);
//            productCategory = pc;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return productCategory;
//    }

    protected boolean updateProductCategoryFromRS(ResultSet rs, ProductCategory pc){
        boolean flag = false;
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int parentId = rs.getInt("parentId");
            int level = rs.getInt("level");
            pc.setId(id);
            pc.setName(name);
            pc.setParentId(parentId);
            pc.setLevel(level);
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }
}
