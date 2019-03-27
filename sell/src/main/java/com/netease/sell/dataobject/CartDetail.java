package com.netease.sell.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Haiyu
 * @date 2019/1/31 20:28
 */
@Data
public class CartDetail implements Serializable {

    private static final long serialVersionUID = 586609454185116719L;

    private String cartDetailId;
    private String userId;
    /** 内容id */
    private String contentId;
    /** 该内容的购买数量 */
    private Integer quantity;
    /** 创建日期 */
    private Date createTime;
    /** 更新日期 */
    private Date updateTime;
}
