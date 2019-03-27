package com.netease.sell.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Haiyu
 * @date 2019/1/31 21:45
 */
@Data
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1393940111846726440L;
    private String orderDetailId;

    /** 所在订单号 */
    private String orderId;

    /**内容id */
    private String contentId;

    /** 内容标题 */
    private String contentTitle;

    /** 内容价格 */
    private BigDecimal contentPrice;

    /** 内容数量 */
    private Integer quantity;

    /** 内容图标 */
    private String contentIcon;

    /** 创建日期 */
    private Date createTime;
    /** 更新日期 */
    private Date updateTime;
}
