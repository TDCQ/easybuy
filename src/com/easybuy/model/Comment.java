package com.easybuy.model;

import java.io.Serializable;
import java.io.StringReader;
import java.util.Date;

/**
 * Created by lenovo on 2016/6/8.
 */
public class Comment implements Serializable {
    private String id;          // 留言编号
    private String reply;       // 回复
    private String content;     // 内容
    private Date createTime;    // 留言时间
    private Date replyTime;     // 回复时间
    private Date nickName;      // 留言用户昵称
}
