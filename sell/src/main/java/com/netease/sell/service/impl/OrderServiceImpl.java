package com.netease.sell.service.impl;

import com.netease.sell.dataobject.ContentInfo;
import com.netease.sell.dataobject.OrderDetail;
import com.netease.sell.dataobject.OrderMaster;
import com.netease.sell.dataobject.UserInfo;
import com.netease.sell.dto.OrderDTO;
import com.netease.sell.enums.OrderStatusEnum;
import com.netease.sell.enums.PayStatusEnum;
import com.netease.sell.enums.ResultEnum;
import com.netease.sell.exception.SellException;
import com.netease.sell.mapper.*;
import com.netease.sell.service.OrderService;
import com.netease.sell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @author Haiyu
 * @date 2019/2/1 21:52
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ContentInfoMapper contentInfoMapper;

    @Autowired
    private CartDetailMapper cartDetailMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(cacheNames = "userNotBuyContentList", key = "#orderDTO.userId"),
            @CacheEvict(cacheNames = "orderDetailList", key = "#orderDTO.userId")})
    public OrderDTO create(OrderDTO orderDTO) {
        // 下单的那一刻就会产生一条订单号
        String orderId = KeyUtil.genUniqueKey();
        // 总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ContentInfo contentInfo = contentInfoMapper.findById(orderDetail.getContentId());
            if (contentInfo == null) {
                throw new SellException(ResultEnum.CONTENT_NOT_EXIST);
            }
            // 2.计算一件商品的总价，遍历所有商品累加得到订单总价
            orderAmount = contentInfo.getContentPrice()
                    .multiply(new BigDecimal(orderDetail.getQuantity()))
                    .add(orderAmount);

            BeanUtils.copyProperties(contentInfo, orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setOrderDetailId(KeyUtil.genUniqueKey());
            orderDetailMapper.create(orderDetail);
            // 3. 减库存
            int leftStock = contentInfo.getContentStock() - orderDetail.getQuantity();
            if (leftStock < 0) {
                throw new SellException(ResultEnum.CONTENT_STOCK_NOT_ENOUGH);
            }
            contentInfo.setContentStock(leftStock);
            contentInfoMapper.updateStock(contentInfo);
            // 4. 清除购物车中的对应内容，并减去购物车总价
            cartDetailMapper.delete(orderDTO.getUserId(), contentInfo.getContentId());
        }
        // 5.写入订单数据（order_master）
        UserInfo userInfo = userInfoMapper.findById(orderDTO.getUserId());
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderDTO.setOrderAmount(orderAmount);
        orderDTO.setUsername(userInfo.getUsername());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMapper.create(orderMaster);
        return orderDTO;
    }

    @Override
    @Cacheable(cacheNames = "orderDetailList", key = "#userId")
    public List<OrderDetail> findOrderByUserId(String userId) {
        return orderDetailMapper.findOrderDetailByUserId(userId);
    }

    @Override
    public OrderDetail findOneOrderDetail(String userId, String contentId) {
        return orderDetailMapper.findOneOrderDetail(userId, contentId);
    }

    @Override
    public Integer findSoldNumByContentId(String contentId) {
        return orderDetailMapper.findSoldNumByContentId(contentId);
    }
}
