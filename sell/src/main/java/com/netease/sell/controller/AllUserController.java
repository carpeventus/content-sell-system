package com.netease.sell.controller;

import com.netease.sell.constant.SessionConstant;
import com.netease.sell.dataobject.ContentInfo;
import com.netease.sell.dataobject.OrderDetail;
import com.netease.sell.dataobject.UserInfo;
import com.netease.sell.enums.ShowTypeEnum;
import com.netease.sell.enums.UserStatusEnum;
import com.netease.sell.service.ContentInfoService;
import com.netease.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Haiyu
 * @date 2019/1/29 13:09
 */
@Controller
@Slf4j
public class AllUserController {
    @Autowired
    private ContentInfoService contentInfoService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String homepage(@RequestParam(value = "type", required = false, defaultValue = "0") Integer type,
                           Model model, HttpSession httpSession) {
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(SessionConstant.USER);
        model.addAttribute("user", userInfo);
        model.addAttribute("type", type);

        List<ContentInfo> contentInfoList = null;
        // 游客，展示所有内容
        if (userInfo == null) {
            contentInfoList = contentInfoService.findAll();
            model.addAttribute("contentInfoList", contentInfoList);
            return "homepage";
        }
        // 如果是卖家，只展示自己发布的内容
        if (UserStatusEnum.SELLER.getCode().equals(userInfo.getIsSeller())) {
            contentInfoList = contentInfoService.findByUser(userInfo.getUserId());
            // 内容售出的件数
            Map<String,Integer> soldNumMap = contentInfoList.stream().collect(Collectors.toMap(ContentInfo::getContentId, contentInfo -> {
                Integer num = orderService.findSoldNumByContentId(contentInfo.getContentId());
                return num == null? 0:num;
            }));
            model.addAttribute("soldNumMap", soldNumMap);
            // 如果是买家
        } else {
            // 且type=1，展示自己未购买过的内容
            if (ShowTypeEnum.NOT_BUY.getCode().equals(type)) {
                contentInfoList = contentInfoService.findUserNotBuy(userInfo.getUserId());
                // 向买家展示所有
            } else {
                contentInfoList = contentInfoService.findAll();
                // 买家购买过的所有内容的id
                Set<String> boughtContentIdSet = orderService.findOrderByUserId(userInfo.getUserId()).stream().map(OrderDetail::getContentId).collect(Collectors.toSet());
                model.addAttribute("contentIdSet", boughtContentIdSet);
            }
        }
        model.addAttribute("contentInfoList", contentInfoList);
        return "homepage";
    }

    @GetMapping("/show/{contentId}")
    public String show(@PathVariable("contentId") String contentId, Model model, HttpSession httpSession) {
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(SessionConstant.USER);
        ContentInfo contentInfo = contentInfoService.findOne(contentId);
        model.addAttribute("user", userInfo);
        model.addAttribute("content", contentInfo);
        return "showContent";
    }
}
