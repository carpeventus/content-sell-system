package com.netease.sell.form;

import lombok.Data;

/**
 * @author Haiyu
 * @date 2019/2/3 12:54
 */
@Data
public class UserForm {
    /** 用户id */
    private String userId;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 是否是卖家，1是卖家，0是买家 */
    private Integer isSeller;
}
