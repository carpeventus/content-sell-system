package com.netease.sell.aspect;

import com.netease.sell.dataobject.UserInfo;
import com.netease.sell.exception.UserAuthorizeException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Haiyu
 * @date 2019/1/25 21:48
 */
@Aspect
@Component
public class UserAuthorizeAspect {
    @Pointcut("execution(* com.netease.sell.controller.User*.*(..))")
    public void verify() {}

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession httpSession = request.getSession();
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("user");
        if (userInfo == null) {
            throw new UserAuthorizeException();
        }
    }
}
