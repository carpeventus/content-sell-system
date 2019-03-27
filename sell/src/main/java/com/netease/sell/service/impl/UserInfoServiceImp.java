package com.netease.sell.service.impl;

import com.netease.sell.dataobject.UserInfo;
import com.netease.sell.form.UserForm;
import com.netease.sell.mapper.UserInfoMapper;
import com.netease.sell.service.UserInfoService;
import com.netease.sell.util.KeyUtil;
import com.netease.sell.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Haiyu
 * @date 2019/1/28 13:52
 */
@Service
public class UserInfoServiceImp implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    @Cacheable(cacheNames = "user", unless = "#result == null")
    public UserInfo findByName(String username) {
        return userInfoMapper.findByName(username);
    }

    @Override
    public UserInfo save(UserForm userForm) {
        UserInfo userInfo = new UserInfo();
        userForm.setUserId(KeyUtil.genUniqueKey());
        // MD5加盐
        userForm.setPassword(MD5Util.md5(userForm.getUserId() + MD5Util.md5(userForm.getPassword())));
        BeanUtils.copyProperties(userForm, userInfo);
        userInfoMapper.save(userInfo);
        return userInfo;
    }
}
