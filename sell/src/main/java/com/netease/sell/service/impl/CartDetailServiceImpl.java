package com.netease.sell.service.impl;

import com.netease.sell.dataobject.CartDetail;
import com.netease.sell.dataobject.ContentInfo;
import com.netease.sell.dto.CartDetailDTO;
import com.netease.sell.enums.ResultEnum;
import com.netease.sell.exception.SellException;
import com.netease.sell.form.CartDetailForm;
import com.netease.sell.mapper.CartDetailMapper;
import com.netease.sell.mapper.ContentInfoMapper;
import com.netease.sell.service.CartDetailService;
import com.netease.sell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Haiyu
 * @date 2019/2/1 10:04
 */
@Service
public class CartDetailServiceImpl implements CartDetailService {
    @Autowired
    private CartDetailMapper cartDetailMapper;
    @Autowired
    private ContentInfoMapper contentInfoMapper;

    @Override
    public CartDetail addToCart(CartDetailForm cartDetailForm) {
        // 1.已经在购物车的内容，更新
        ContentInfo contentInfo = contentInfoMapper.findById(cartDetailForm.getContentId());
        CartDetail cartDetail = cartDetailMapper.findByContentId(cartDetailForm.getUserId(), cartDetailForm.getContentId());

        if (cartDetail != null) {
            // 在原来的数量上增长
            Integer newQuantity = cartDetailForm.getQuantity() + cartDetail.getQuantity();
            if (newQuantity > contentInfo.getContentStock()) {
                throw new SellException(ResultEnum.CONTENT_STOCK_NOT_ENOUGH);
            }
            // 复制属性
            cartDetailForm.setCartDetailId(cartDetail.getCartDetailId());
            cartDetailForm.setQuantity(newQuantity);
            BeanUtils.copyProperties(cartDetailForm, cartDetail);
            cartDetailMapper.updateOne(cartDetail);
            // 2. 否则添加的是新的内容，插入
        } else {
            if (cartDetailForm.getQuantity() > contentInfo.getContentStock()) {
                throw new SellException(ResultEnum.CONTENT_STOCK_NOT_ENOUGH);
            }
            cartDetail = new CartDetail();
            cartDetailForm.setCartDetailId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(cartDetailForm, cartDetail);
            cartDetailMapper.addToCart(cartDetail);
        }
        return cartDetail;
    }

    @Override
    public List<CartDetailDTO> findCartDetails(String userId) {
        return cartDetailMapper.findCartDetails(userId);
    }

    @Override
    public CartDetail updateOne(CartDetail cartDetail) {
        cartDetailMapper.updateOne(cartDetail);
        return cartDetail;
    }

    @Override
    public CartDetail delete(String userId, String contentId) {
        CartDetail cartDetail = cartDetailMapper.findByContentId(userId, contentId);
        cartDetailMapper.delete(userId, contentId);
        return cartDetail;
    }

    @Override
    public CartDetail findByContentId(String userId, String contentId) {
        return cartDetailMapper.findByContentId(userId,contentId);
    }
}
