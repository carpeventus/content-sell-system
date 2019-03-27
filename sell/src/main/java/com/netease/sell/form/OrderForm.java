package com.netease.sell.form;

import lombok.Data;

/**
 * @author Haiyu
 * @date 2018/10/19 8:57
 */
@Data
public class OrderForm {
    /** 用户名 */
    private String userId;
    /** 购物车 */
    private String items;
}
