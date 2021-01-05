package com.motang.auth.controller;

import com.motang.auth.service.IValidateCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 *  @Description 认证相关接口
 *  @author liuhu
 *  @Date 2021/1/5 16:32
 */
@RestController
@Api(tags = "认证相关接口")
public class AuthController {

    @Autowired
    private IValidateCodeService validateCodeService;


    @GetMapping("captcha")
    @ApiOperation("生成验证2码接口")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        validateCodeService.create(request, response);
    }

    @ApiOperation("资源微服务认证接口")
    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }
}
