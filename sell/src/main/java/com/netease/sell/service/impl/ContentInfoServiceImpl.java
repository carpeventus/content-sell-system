package com.netease.sell.service.impl;

import com.netease.sell.dataobject.ContentInfo;
import com.netease.sell.dataobject.OrderDetail;
import com.netease.sell.form.ContentInfoForm;
import com.netease.sell.mapper.ContentInfoMapper;
import com.netease.sell.service.ContentInfoService;
import com.netease.sell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Haiyu
 * @date 2019/1/28 13:59
 */
@Service
@Slf4j
public class ContentInfoServiceImpl implements ContentInfoService {
    @Autowired
    private ContentInfoMapper contentInfoMapper;

    @Override
    public ContentInfo save(ContentInfoForm contentInfoForm) {
        ContentInfo contentInfo = new ContentInfo();
        BeanUtils.copyProperties(contentInfoForm, contentInfo);
        contentInfo.setContentId(KeyUtil.genUniqueKey());
        contentInfoMapper.save(contentInfo);
        return contentInfo;
    }

    @Override
    @Cacheable(cacheNames = "contentList", key = "T(String).valueOf('all')")
    public List<ContentInfo> findAll() {
        return contentInfoMapper.findAll();
    }

    @Override
    public ContentInfo findOne(String contentId) {
        return contentInfoMapper.findById(contentId);
    }

    @Override
    @CacheEvict(cacheNames = "contentList", key = "T(String).valueOf('all')")
    public ContentInfo deleteOne(String contentId) {
        ContentInfo contentInfo = contentInfoMapper.findById(contentId);
        contentInfoMapper.deleteById(contentId);
        return contentInfo;
    }

    /**
     * 更新内容
     *
     * @param contentInfoForm
     * @return
     */
    @Override
    @CacheEvict(cacheNames = "contentList", key = "T(String).valueOf('all')")
    public ContentInfo updateOne(ContentInfoForm contentInfoForm) {
        ContentInfo contentInfo = contentInfoMapper.findById(contentInfoForm.getContentId());
        BeanUtils.copyProperties(contentInfoForm, contentInfo);
        contentInfoMapper.update(contentInfo);
        return contentInfo;
    }

    /**
     * 查询某个卖家发布的所有的内容
     *
     * @param userId
     * @return
     */
    @Override
    public List<ContentInfo> findByUser(String userId) {
        return contentInfoMapper.findByUser(userId);
    }

    /**
     * 查询买家未购买过的内容
     * @param userId
     * @return
     */
    @Override
    @Cacheable(cacheNames = "userNotBuyContentList", key = "#userId")
    public List<ContentInfo> findUserNotBuy(String userId) {
        return contentInfoMapper.findUserNotBuy(userId);
    }


}
