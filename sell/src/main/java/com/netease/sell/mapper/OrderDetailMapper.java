package com.netease.sell.mapper;

import com.netease.sell.dataobject.OrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Haiyu
 * @date 2019/2/1 21:38
 */
public interface OrderDetailMapper {
    @Insert("insert into order_detail(order_detail_id, order_id,content_id,content_title,content_price,content_icon,quantity) " +
            "values(#{orderDetailId}, #{orderId}, #{contentId},#{contentTitle},#{contentPrice},#{contentIcon},#{quantity})")
    int create(OrderDetail orderDetail);

    @Results(id = "orderDetailResultMap", value = {
            @Result(property = "orderDetailId", column = "order_detail_id", id = true),
            @Result(property = "contentId", column = "content_id"),
            @Result(property = "contentTitle", column = "content_title"),
            @Result(property = "contentPrice", column = "content_price"),
            @Result(property = "contentIcon", column = "content_icon"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "createTime", column = "create_time")
    })
    /** 查询自己的购买记录,按照时间降序，订单状态必须是已完结 */
    @Select("SELECT order_detail_id, content_id, content_title, content_icon,content_price,quantity,od.create_time FROM order_master om LEFT JOIN order_detail od ON om.order_id=od.order_id WHERE om.user_id=#{userId} AND om.order_status=1 ORDER BY od.create_time DESC")
    List<OrderDetail> findOrderDetailByUserId(String userId);

    /** 查询包含某个内容的订单详情 */
    @ResultMap("orderDetailResultMap")
    @Select("SELECT order_detail_id, content_id, content_title, content_icon,content_price,quantity,od.create_time FROM order_master om LEFT JOIN order_detail od ON om.order_id=od.order_id WHERE om.user_id=#{userId} and content_id=#{contentId}")
    OrderDetail findOneOrderDetail(@Param("userId") String userId, @Param("contentId") String contentId);

    /** 计算某个内容被购买了多少件, 订单状态必须是已完结 */
    @Select("SELECT SUM(quantity) num FROM order_detail od left join order_master om on od.order_id=om.order_id WHERE om.order_status=1 and od.content_id=#{contentId}")
    Integer findSoldNumByContentId(String contentId);
}
