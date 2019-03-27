package com.netease.sell.mapper;

import com.netease.sell.dataobject.CartDetail;
import com.netease.sell.dto.CartDetailDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Haiyu
 * @date 2019/2/1 9:37
 */
public interface CartDetailMapper {
    @Insert("insert into cart_detail(cart_detail_id,user_id, content_id,quantity)" +
            " values(#{cartDetailId}, #{userId}, #{contentId},#{quantity})")
    int addToCart(CartDetail cartDetail);

    /** 根据内容id查询购物车详情 */
    @Results(id="cartDetailResultMap", value = {
            @Result(property = "cartDetailId", column = "cart_detail_id", id = true),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "contentId", column = "content_id"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    @Select("select * from cart_detail where user_id=#{userId} and content_id=#{contentId}")
    CartDetail findByContentId(@Param("userId") String userId, @Param("contentId") String contentId);

    @Update("update cart_detail set quantity=#{quantity, jdbcType=INTEGER} where cart_detail_id=#{cartDetailId}")
    int updateOne(CartDetail cartDetail);

    @Results(id="cartDetailDTOMap", value = {
            @Result(property = "cartDetailId", column = "cart_detail_id", id = true),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "contentId", column = "content_id"),
            @Result(property = "contentTitle", column = "content_title"),
            @Result(property = "contentPrice", column = "content_price"),
            @Result(property = "contentIcon", column = "content_icon"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    @Select("select cd.*, ci.content_title,ci.content_price,ci.content_icon from cart_detail cd left join content_info ci on cd.content_id=ci.content_id " +
            "where cd.user_id = #{userId} order by cd.create_time desc")
    List<CartDetailDTO> findCartDetails(String userId);

    @Delete("delete from cart_detail where user_id=#{userId} and content_id=#{contentId}")
    int delete(@Param("userId") String userId, @Param("contentId") String contentId);
}
