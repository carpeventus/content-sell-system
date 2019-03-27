package com.netease.sell.dto;

import com.netease.sell.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Haiyu
 * @date 2019/2/1 19:47
 */

@Data
public class OrderDTO {
    private String orderId;
    private String userId;

    /**
     * 买家姓名
     */
    private String username;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态，默认0是新下单
     */
    private Integer orderStatus;

    /**
     * 订单支付状态，默认未支付
     */
    private Integer payStatus;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单更新时间
     */
    private Date updateTime;

    /**
     * 一个订单对应的多个订单详情
     */
    private List<OrderDetail> orderDetailList;

}
