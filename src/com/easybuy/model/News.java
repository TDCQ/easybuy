package com.easybuy.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2016/6/8.
 */
public class News implements Serializable {
    private int id;             // 编号
    private String title;       // 标题
    private String content;     // 内容
    private Date createDate;    // 创建时间
}
