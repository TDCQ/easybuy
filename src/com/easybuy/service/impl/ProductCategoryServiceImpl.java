package com.easybuy.service.impl;

import com.easybuy.dao.ProductCategoryDao;
import com.easybuy.dao.impl.ProductCategoryDaoImpl;
import com.easybuy.model.ProductCategory;
import com.easybuy.service.ProductCategoryService;

import java.util.List;

/**
 * Created by lenovo on 2016/6/15.
 */
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoImpl();

    @Override
    public boolean addProductCategory(ProductCategory productCategory) {
        return productCategoryDao.addProductCategory(productCategory);
    }

    @Override
    public boolean delProductCategory(ProductCategory productCategory) {
        return productCategoryDao.delProductCategory(productCategory);
    }

    @Override
    public boolean updateProductCategory(ProductCategory productCategory) {
        return productCategoryDao.updateProductCategory(productCategory);
    }

    @Override
    public ProductCategory getProductCategoryById(Integer id) {
        return productCategoryDao.getProductCategoryById(id);
    }

    @Override
    public List<ProductCategory> getProductCategorys() {
        return productCategoryDao.getProductCategorys();
    }

    @Override
    public List<ProductCategory> getProductCategoryFromRoot(ProductCategory productCategory) {
        return productCategoryDao.getProductCategoryFromRoot(productCategory);
    }

    @Override
    public List<ProductCategory> getProductCategoryByLevel(Integer level) {
        return productCategoryDao.getProductCategoryByLevel(level);
    }
}
