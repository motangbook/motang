package com.motang.auth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  @Description 验证码接口
 *  @author liuhu
 *  @Date 2021/1/5 16:36
 */
public interface IValidateCodeService {
    /**
     * @Description 生成验证码
     * @author liuhu
     * @param request
     * @param response
     * @date 2021/1/5 16:38
     * @return void
     */
    void create(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * @Description 校验验证码
     * @author liuhu
     * @param key
     * @param code
     * @date 2021/1/5 16:39
     * @return void
     */
    void check(String key, String code) throws Exception;
}
