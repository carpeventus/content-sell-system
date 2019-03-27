package com.netease.sell.service;

import com.netease.sell.dataobject.OrderDetail;
import com.netease.sell.dto.OrderDTO;

import java.util.List;

/**
 * @author Haiyu
 * @date 2019/2/1 21:51
 */
public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);
    List<OrderDetail> findOrderByUserId(String userId);
    OrderDetail findOneOrderDetail(String userId, String contentId);
    Integer findSoldNumByContentId(String contentId);
}
