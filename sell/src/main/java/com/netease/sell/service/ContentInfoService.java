package com.netease.sell.service;

import com.netease.sell.dataobject.ContentInfo;
import com.netease.sell.dataobject.OrderDetail;
import com.netease.sell.form.ContentInfoForm;

import java.util.List;

/**
 * @author Haiyu
 * @date 2019/1/28 13:59
 */
public interface ContentInfoService {
    ContentInfo save(ContentInfoForm contentInfoForm);
    List<ContentInfo> findAll();
    ContentInfo findOne(String contentId);
    ContentInfo deleteOne(String contentId);
    ContentInfo updateOne(ContentInfoForm contentInfoForm);
    List<ContentInfo> findByUser(String userId);
    List<ContentInfo> findUserNotBuy(String userId);

}
