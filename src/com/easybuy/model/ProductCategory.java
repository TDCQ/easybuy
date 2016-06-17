package com.easybuy.model;

import java.util.List;

/**
 * Created by lenovo on 2016/6/9.
 */
public class ProductCategory {
    private Integer id;         // 商品分类编号
    private String name;        // 商品分类名字
    private Integer parentId;   // 父商品分类名字
    private Integer level;      // 所属分类的级别

    public ProductCategory(){}

    public ProductCategory(Integer id, String name, Integer parentId, Integer level) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
