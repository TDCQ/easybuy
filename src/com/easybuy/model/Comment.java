package com.easybuy.model;

import java.io.Serializable;
import java.io.StringReader;
import java.util.Date;

/**
 * Created by lenovo on 2016/6/8.
 */
public class Comment implements Serializable {
    private Long id;          // 留言编号*
    private String title;       // 标题*
    private String content;     // 内容*
    private Date createTime;    // 留言时间*
    private Long userId;        // 留言用户id
    private String nickName;    // 留言用户昵称 *
    private Long reply;             // 目标人的Id
    private String replyTitle;      // 目标留言
}
