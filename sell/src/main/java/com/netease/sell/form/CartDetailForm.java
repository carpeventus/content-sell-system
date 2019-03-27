package com.netease.sell.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Haiyu
 * @date 2019/2/1 10:10
 */
@Data
public class CartDetailForm {
    private String cartDetailId;
    /** 购物车拥有者 */
    private String userId;
    /** 内容id */
    private String contentId;
    /** 该内容的购买数量 */
    private Integer quantity;
}
