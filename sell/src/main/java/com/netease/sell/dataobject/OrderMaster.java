package com.netease.sell.dataobject;

import com.netease.sell.enums.OrderStatusEnum;
import com.netease.sell.enums.PayStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Haiyu
 * @date 2019/1/31 21:39
 */
@Data
public class OrderMaster implements Serializable {

    private static final long serialVersionUID = 4403896516422872762L;
    /** 订单号 */
    private String orderId;

    private String userId;
    /** 下单买家 */
    private String username;
    /** 订单总金额 */
    private BigDecimal orderAmount;
    /** 订单状态，默认0是新下单 */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 订单支付状态，默认未支付 */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    /** 创建日期 */
    private Date createTime;
    /** 更新日期 */
    private Date updateTime;


}
