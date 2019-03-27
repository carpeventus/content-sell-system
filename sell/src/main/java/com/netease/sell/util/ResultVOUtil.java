package com.netease.sell.util;


import com.netease.sell.enums.ResultEnum;
import com.netease.sell.vo.ResultVO;

/**
 * @author Haiyu
 * @date 2018/10/16 10:42
 */
public class ResultVOUtil {
    public static ResultVO success(String msg, Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMsg(msg);
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(String msg) {
        return success(msg, null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
