package com.easybuy.service;

import com.easybuy.model.ProductCategory;

import java.util.List;

/**
 * Created by lenovo on 2016/6/15.
 */
public interface ProductCategoryService {

    boolean addProductCategory(ProductCategory productCategory);

    boolean delProductCategory(ProductCategory productCategory);

    boolean updateProductCategory(ProductCategory productCategory);

    ProductCategory getProductCategoryById(Integer id);

    List<ProductCategory> getProductCategorys();

    List<ProductCategory> getProductCategoryFromRoot(ProductCategory productCategory);

    List<ProductCategory> getProductCategoryByLevel(Integer level);
}
