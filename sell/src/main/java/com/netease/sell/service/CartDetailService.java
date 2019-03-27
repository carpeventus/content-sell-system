package com.netease.sell.service;

import com.netease.sell.dataobject.CartDetail;
import com.netease.sell.dto.CartDetailDTO;
import com.netease.sell.form.CartDetailForm;

import java.util.List;

/**
 * @author Haiyu
 * @date 2019/2/1 10:04
 */
public interface CartDetailService {
    CartDetail addToCart(CartDetailForm cartDetailForm);
    List<CartDetailDTO> findCartDetails(String userId);
    CartDetail updateOne(CartDetail cartDetail);
    CartDetail delete(String userId, String contentId);
    CartDetail findByContentId(String userId, String contentId);
}
