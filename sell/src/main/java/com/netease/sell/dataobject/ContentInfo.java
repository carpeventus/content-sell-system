package com.netease.sell.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Haiyu
 * @date 2019/1/19 17:22
 */
@Data
public class ContentInfo implements Serializable {

    private static final long serialVersionUID = 7304164511382281887L;
    /** 内容id */
    private String contentId;
    /** 内容发布者id */
    private String userId;
    /** 内容标题 */
    private String contentTitle;
    /** 内容摘要 */
    private String contentSummary;
    /** 内容正文 */
    private String contentDescription;
    /** 内容价格 */
    private BigDecimal contentPrice;
    /** 内容库存 */
    private Integer contentStock;
    /** 内容图标 */
    private String contentIcon;
    /** 创建日期 */
    private Date createTime;
    /** 更新日期 */
    private Date updateTime;

}
