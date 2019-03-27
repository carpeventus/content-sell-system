package com.netease.sell.enums;

import lombok.Getter;

/**
 * @author Haiyu
 * @date 2019/1/30 14:48
 */
@Getter
public enum  UserStatusEnum implements CodeEnum{
    /** 内容状态 - 未售出 */
    BUYER(0, "买家"),
    /** 内容状态 - 已售出 */
    SELLER(1, "卖家")
    ;

    private Integer code;
    private String status;

    UserStatusEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }
}
