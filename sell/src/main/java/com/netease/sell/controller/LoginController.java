package com.netease.sell.controller;

import com.netease.sell.constant.SessionConstant;
import com.netease.sell.dataobject.UserInfo;
import com.netease.sell.enums.ResultEnum;
import com.netease.sell.exception.SellException;
import com.netease.sell.form.UserForm;
import com.netease.sell.service.UserInfoService;
import com.netease.sell.util.KeyUtil;
import com.netease.sell.util.MD5Util;
import com.netease.sell.util.ResultVOUtil;
import com.netease.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author Haiyu
 * @date 2019/1/19 17:37
 */
@Controller
@Slf4j
public class LoginController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/registerSubmit")
    @ResponseBody
    public ResultVO registerSubmit(UserForm userForm) {

        UserInfo user = userInfoService.findByName(userForm.getUsername());
        if (user != null) {
            throw new SellException(ResultEnum.REGISTER_USERNAME_ERROR);
        }
        userInfoService.save(userForm);
        return ResultVOUtil.success("注册成功！\n即将跳转到登录界面~");
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/checkLogin")
    @ResponseBody
    public ResultVO checkLogin(UserForm userForm, HttpSession httpSession) {

        UserInfo user = userInfoService.findByName(userForm.getUsername());
        if (user == null) {
            throw new SellException(ResultEnum.LOGIN_USERNAME_ERROR);
        }
        // MD5加密
        String password = MD5Util.md5(user.getUserId() + MD5Util.md5(userForm.getPassword()));
        log.info("password={}", password);
        if (!password.equals(user.getPassword())) {
            throw new SellException(ResultEnum.LOGIN_PASSWORD_ERROR);
        }
        httpSession.setAttribute(SessionConstant.USER, user);
        return ResultVOUtil.success("登录成功");
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {

        httpSession.removeAttribute(SessionConstant.USER);
        return "redirect:/login";
    }

}
