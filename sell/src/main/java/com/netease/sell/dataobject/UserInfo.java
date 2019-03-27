package com.netease.sell.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Haiyu
 * @date 2019/1/19 17:19
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 8167691075237077806L;

    /** 用户id */
    private String userId;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 是否是卖家，1是卖家，0是买家 */
    private Integer isSeller;
    /** 创建日期 */
    private Date createTime;
    /** 更新日期 */
    private Date updateTime;

}
