package com.netease.sell.mapper;

import com.netease.sell.dataobject.OrderMaster;
import org.apache.ibatis.annotations.Insert;

/**
 * @author Haiyu
 * @date 2019/2/1 21:38
 */
public interface OrderMapper {
    @Insert("insert into order_master(order_id, user_id, username, order_amount, order_status,pay_status) " +
            "values(#{orderId}, #{userId}, #{username}, #{orderAmount}, #{orderStatus}, #{payStatus})")
    int create(OrderMaster orderMaster);
}
