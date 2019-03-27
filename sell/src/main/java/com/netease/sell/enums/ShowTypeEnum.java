package com.netease.sell.enums;

import lombok.Getter;

/**
 * @author Haiyu
 * @date 2019/3/15 10:28
 */
@Getter
public enum ShowTypeEnum implements CodeEnum {
    ALL(0, "全部"),
    NOT_BUY(1,"未购买"),
    BOUGHT(2, "已购买"),
    SOLD(3, "已卖出"),
    UNSOLD(4, "未卖出"),
    ;

    private Integer code;
    private String msg;

    ShowTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
