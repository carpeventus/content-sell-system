package com.netease.sell.mapper;

import com.netease.sell.dataobject.ContentInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Haiyu
 * @date 2019/1/28 13:59
 */
public interface ContentInfoMapper {
    /**
     * 保存一条内容
     */
    @Insert("insert into content_info(content_id,user_id,content_title,content_summary, content_price, content_stock, content_description,content_icon)" +
            " values(#{contentId},#{userId},#{contentTitle},#{contentSummary},#{contentPrice}, #{contentStock},#{contentDescription},#{contentIcon})")
    int save(ContentInfo contentInfo);

    /**
     * 查询所有内容
     *
     * @return
     */
    @Results(id = "contentResultMap", value = {
            @Result(property = "contentId", column = "content_id", id = true),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "contentTitle", column = "content_title"),
            @Result(property = "contentSummary", column = "content_summary"),
            @Result(property = "contentPrice", column = "content_price"),
            @Result(property = "contentStock", column = "content_stock"),
            @Result(property = "contentIcon", column = "content_icon"),
            @Result(property = "contentDescription", column = "content_description"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")})
    @Select("select * from content_info order by create_time desc")
    List<ContentInfo> findAll();

    /**
     * 根据id查询内容
     *
     * @param contentId 指定的内容id
     * @return
     */
    @ResultMap("contentResultMap")
    @Select("select * from content_info where content_id = #{contentId}")
    ContentInfo findById(String contentId);

    @ResultMap("contentResultMap")
    @Select("select * from content_info where user_id=#{userId} order by create_time desc")
    List<ContentInfo> findByUser(String userId);

    @ResultMap("contentResultMap")
    @Select("SELECT * FROM content_info WHERE content_id NOT IN (SELECT od.content_id FROM order_detail od LEFT JOIN order_master om ON od.order_id=om.order_id WHERE user_id=#{userId}) order by create_time desc")
    List<ContentInfo> findUserNotBuy(String userId);

    /**
     * 根据id删除内容
     *
     * @param contentId 指定的内容id
     * @return
     */
    @Delete("delete from content_info where content_id = #{contentId}")
    int deleteById(String contentId);

    @Update("update content_info set content_title=#{contentTitle}, content_summary=#{contentSummary}," +
            "content_price=#{contentPrice}, content_stock=#{contentStock}, content_icon=#{contentIcon},content_description=#{contentDescription} " +
            "where content_id=#{contentId}")
    int update(ContentInfo contentInfo);


    @Update("update content_info set content_stock=#{contentStock} where content_id=#{contentId}")
    int updateStock(ContentInfo contentInfo);
}
