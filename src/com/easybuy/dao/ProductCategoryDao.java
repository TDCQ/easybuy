package com.easybuy.dao;

import com.easybuy.model.ProductCategory;

import java.util.List;

/**
 * 商品类别管理
 * Created by lenovo on 2016/6/15.
 */
public interface ProductCategoryDao {
    public boolean addProductCategory(ProductCategory productCategory);

    public boolean delProductCategory(ProductCategory productCategory);

    public boolean updateProductCategory(ProductCategory productCategory);

    public ProductCategory getProductCategoryById(Integer id);

    public List<ProductCategory> getProductCategorys();

    public List<ProductCategory> getProductCategoryFromRoot(ProductCategory productCategory);

    public List<ProductCategory> getProductCategoryByLevel(Integer level);
}
