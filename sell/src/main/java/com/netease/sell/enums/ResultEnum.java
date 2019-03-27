package com.netease.sell.enums;

import lombok.Getter;

/**
 * @author Haiyu
 * @date 2019/1/31 19:04
 */
@Getter
public enum ResultEnum implements CodeEnum{
    SUCCESS(200, "成功"),
    LOGIN_USERNAME_ERROR(100, "用户名不存在"),
    LOGIN_PASSWORD_ERROR(101, "密码错误"),
    REGISTER_USERNAME_ERROR(102, "该用户名已被注册"),
    UPLOAD_NO_FILE_ERROR(301, "未选择任何文件"),
    ORDER_PARAM_ERROR(400, "订单参数不正确"),
    ORDER_CART_EMPTY(401,"未选择任何内容！"),
    CONTENT_NOT_EXIST(500,"内容不存在"),
    CONTENT_STOCK_NOT_ENOUGH(501,"库存不足！")
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    ResultEnum(Integer code) {
        this.code = code;
    }
}
