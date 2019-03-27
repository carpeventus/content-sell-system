package com.netease.sell.exception;

import com.netease.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * @author Haiyu
 * @date 2018/10/17 9:56
 */
@Getter
public class SellException extends RuntimeException {
    private Integer code;
    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
