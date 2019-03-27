package com.netease.sell.mapper;

import com.netease.sell.dataobject.UserInfo;
import org.apache.ibatis.annotations.*;

/**
 * @author Haiyu
 * @date 2019/1/21 21:23
 */
public interface UserInfoMapper {
    @Insert("insert into user_info(user_id, username,password,is_seller) " +
            "values(#{userId}, #{username}, #{password}, #{isSeller})")
    int save(UserInfo userInfo);

    /** 根据用户名搜索某个用户 */
    @Results(id = "userInfoResultMap", value = {
            @Result(column = "user_id", property = "userId",id = true),
            @Result(column = "is_seller", property = "isSeller"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("select * from user_info where username = #{username}")
    UserInfo findByName(String username);

    @Select("select * from user_info where user_id = #{userId}")
    UserInfo findById(String userId);

}
