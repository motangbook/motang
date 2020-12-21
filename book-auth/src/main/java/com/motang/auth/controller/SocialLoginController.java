package com.motang.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("social")
public class SocialLoginController {
    

    @RequestMapping("callback")
    public String aa(HttpServletRequest request){
        // 1得到第三放用户信息  然后注册用户  新增第三方客户端信息 然后调用 tokenrequest  获取密码模式的token
        return  request.getParameter("code");
    }
}
