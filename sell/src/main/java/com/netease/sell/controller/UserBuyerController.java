package com.netease.sell.controller;

import com.netease.sell.constant.SessionConstant;
import com.netease.sell.converter.OrderForm2OrderDTO;
import com.netease.sell.dataobject.CartDetail;
import com.netease.sell.dataobject.ContentInfo;
import com.netease.sell.dataobject.OrderDetail;
import com.netease.sell.dataobject.UserInfo;
import com.netease.sell.dto.CartDetailDTO;
import com.netease.sell.dto.OrderDTO;
import com.netease.sell.enums.ResultEnum;
import com.netease.sell.exception.SellException;
import com.netease.sell.form.CartDetailForm;
import com.netease.sell.form.OrderForm;
import com.netease.sell.service.CartDetailService;
import com.netease.sell.service.ContentInfoService;
import com.netease.sell.service.OrderService;
import com.netease.sell.util.ResultVOUtil;
import com.netease.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @author Haiyu
 * @date 2019/1/26 14:21
 */
@Controller
@Slf4j
public class UserBuyerController {
    @Autowired
    private CartDetailService cartDetailService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ContentInfoService contentInfoService;

    @PostMapping("/addToCart")
    @ResponseBody
    public ResultVO addToCart(CartDetailForm cartDetailForm) {
        cartDetailService.addToCart(cartDetailForm);
        return ResultVOUtil.success("添加成功");
    }

    @GetMapping("/settleAccount")
    public String settleAccount(Model model, HttpSession httpSession) {
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(SessionConstant.USER);
        model.addAttribute("user", userInfo);
        List<CartDetailDTO> cartDetailList = cartDetailService.findCartDetails(userInfo.getUserId());
        model.addAttribute("cartDetailList", cartDetailList);
        return "buyer/settleAccount";
    }

    @PostMapping("/buy")
    @ResponseBody
    public ResultVO buy(OrderForm orderForm) {
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.ORDER_CART_EMPTY);
        }

        orderService.create(orderDTO);
        return ResultVOUtil.success("购买成功");
    }

    @GetMapping("/account")
    public String account(Model model, HttpSession httpSession) {
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(SessionConstant.USER);
        model.addAttribute("user", userInfo);
        List<OrderDetail> orderDetailList = orderService.findOrderByUserId(userInfo.getUserId());
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDetailList) {
            amount = orderDetail.getContentPrice().multiply(new BigDecimal(orderDetail.getQuantity())).add(amount);
        }
        model.addAttribute("orderDetailList", orderDetailList);
        model.addAttribute("amount", amount);
        return "buyer/account";
    }

    @PostMapping("/deleteCartDetail")
    @ResponseBody
    public ResultVO deleteCartDetail(@RequestParam("userId") String userId, @RequestParam("contentId") String contentId) {
        cartDetailService.delete(userId, contentId);
        return ResultVOUtil.success("删除成功");
    }

    @PostMapping("/saveCartDetail")
    @ResponseBody
    public ResultVO saveCartDetail(@RequestParam("userId") String userId,
                                   @RequestParam("contentId") String contentId,
                                   @RequestParam("quantity") Integer quantity) {
        CartDetail cartDetail = cartDetailService.findByContentId(userId, contentId);
        ContentInfo contentInfo = contentInfoService.findOne(contentId);
        // 不能设置比库存大的购买量
        if (quantity > contentInfo.getContentStock()) {
            throw new SellException(ResultEnum.CONTENT_STOCK_NOT_ENOUGH.getCode(),ResultEnum.CONTENT_STOCK_NOT_ENOUGH.getMsg() + "最多可购买" + contentInfo.getContentStock() + "件");
        }
        cartDetail.setQuantity(quantity);
        cartDetailService.updateOne(cartDetail);
        return ResultVOUtil.success("修改成功");
    }
}
