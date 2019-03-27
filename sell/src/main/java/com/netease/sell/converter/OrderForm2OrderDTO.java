package com.netease.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netease.sell.dataobject.OrderDetail;
import com.netease.sell.dto.OrderDTO;
import com.netease.sell.enums.ResultEnum;
import com.netease.sell.exception.SellException;
import com.netease.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Haiyu
 * @date 2018/10/19 9:11
 */
@Slf4j
public class OrderForm2OrderDTO {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(orderForm.getUserId());

        Gson gson = new Gson();
        List<OrderDetail> orderDetailList = null;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("【对象装换】错误，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.ORDER_PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
