package com.netease.sell.handler;

import com.netease.sell.exception.SellException;
import com.netease.sell.exception.UserAuthorizeException;
import com.netease.sell.util.ResultVOUtil;
import com.netease.sell.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Haiyu
 * @date 2018/10/30 15:16
 */
@ControllerAdvice
public class SellerExceptionHandler {
    // 拦截登录异常，若发生异常，说明用户未登录，则引导用户重新登录
    @ExceptionHandler(UserAuthorizeException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handlerAuthorizeException() {
        return "redirect:/login";
    }

    @ExceptionHandler(SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e) {
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
