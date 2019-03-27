package com.netease.sell.controller;

import com.netease.sell.constant.SessionConstant;
import com.netease.sell.dataobject.ContentInfo;
import com.netease.sell.dataobject.UserInfo;
import com.netease.sell.form.ContentInfoForm;
import com.netease.sell.service.ContentInfoService;
import com.netease.sell.util.ResultVOUtil;
import com.netease.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author Haiyu
 * @date 2019/1/26 14:20
 */
@Controller
@Slf4j
public class UserSellerController {
    @Autowired
    private ContentInfoService contentInfoService;

    @GetMapping("/public")
    public String publicContent(Model model, HttpSession httpSession) {
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(SessionConstant.USER);
        model.addAttribute("user",userInfo);
        return "seller/publicContent";
    }

    @PostMapping("/publicSubmit")
    public String publicSubmit(ContentInfoForm contentInfoForm, HttpSession httpSession,Model model) {
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(SessionConstant.USER);
        model.addAttribute("user",userInfo);
        contentInfoForm.setUserId(userInfo.getUserId());
        ContentInfo contentInfo = contentInfoService.save(contentInfoForm);
        model.addAttribute("content", contentInfo);
        return "seller/publicSuccess";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String contentId,HttpSession httpSession,Model model) {
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(SessionConstant.USER);
        model.addAttribute("user",userInfo);
        ContentInfo content = contentInfoService.findOne(contentId);
        model.addAttribute("content", content);
        return "seller/editContent";
    }

    @PostMapping("/editSubmit/{id}")
    public String editSubmit(@PathVariable("id") String contentId,ContentInfoForm contentInfoForm,HttpSession httpSession,Model model) {
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(SessionConstant.USER);
        contentInfoForm.setContentId(contentId);
        ContentInfo contentInfo = contentInfoService.updateOne(contentInfoForm);
        model.addAttribute("user",userInfo);
        model.addAttribute("content", contentInfo);
        return "seller/editSuccess";
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResultVO delete(@RequestParam("contentId") String contentId) {
        contentInfoService.deleteOne(contentId);
        return ResultVOUtil.success("删除成功");
    }
}
