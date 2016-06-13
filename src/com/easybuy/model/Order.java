package com.easybuy.model;

import java.util.Date;

/**
 * Created by lenovo on 2016/6/9.
 */
public class Order {
    private Long id;
    private Long userId;
    private String userName;
    private String userAddress;
    private Date createTime;
    private Double cost;
//    private Enum<String> status;  // 状态：  1、下单， 2、审核通过， 3、配货 4、送货中 5、收货并确认
//  private Enum<String> type;  // 付款方式：1， 货到付款； 2、网上支付
}
