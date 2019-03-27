package com.netease.sell.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Haiyu
 * @date 2019/3/15 13:29
 */
@Data
public class CartDetailDTO {
    private String cartDetailId;
    /** 购物车拥有者 */
    private String userId;
    /** 内容id */
    private String contentId;

    /** 内容标题 */
    private String contentTitle;

    /** 内容价格 */
    private BigDecimal contentPrice;
    /** 内容图标 */
    private String contentIcon;
    /** 该内容的购买数量 */
    private Integer quantity;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单更新时间
     */
    private Date updateTime;
}
