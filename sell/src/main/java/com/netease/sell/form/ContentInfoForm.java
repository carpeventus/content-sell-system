package com.netease.sell.form;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 内容发布的表单对应实体类
 *
 * @author Haiyu
 * @date 2019/1/28 12:51
 */
@Data
public class ContentInfoForm {
    private String contentId;
    private String userId;
    /** 内容标题 */
    private String contentTitle;
    /** 内容摘要 */
    private String contentSummary;
    /** 内容正文 */
    private String contentDescription;
    /** 内容库存 */
    private Integer contentStock;
    /** 内容价格 */
    private BigDecimal contentPrice;
    /** 内容图标 */
    private String contentIcon;
}
