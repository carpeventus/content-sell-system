package com.netease.sell.service;

import com.netease.sell.dataobject.UserInfo;
import com.netease.sell.form.UserForm;

/**
 * @author Haiyu
 * @date 2019/1/28 13:51
 */
public interface UserInfoService {
    UserInfo findByName(String username);
    UserInfo save(UserForm userForm);
}
