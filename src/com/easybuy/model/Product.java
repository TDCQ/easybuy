package com.easybuy.model;

import java.io.Serializable;

/**
 * Created by lenovo on 2016/6/8.
 */
public class Product implements Serializable {
    private int id;
    private String name;
    private String description;
    private double price;
    private double stock;
    private int firstcategoryId;    // 一级分类id;
    private int secondCategoryId;   // 二级分类id
    private String filename;        // 商品图片名
}
