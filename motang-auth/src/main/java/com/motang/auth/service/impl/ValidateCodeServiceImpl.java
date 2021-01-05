package com.motang.auth.service.impl;

import com.motang.auth.constant.AuthConstants;
import com.motang.auth.constant.CaptchaConstant;
import com.motang.auth.exception.AuthorizeException;
import com.motang.auth.properties.CaptchaProperties;
import com.motang.auth.service.IValidateCodeService;
import com.motang.common.redis.service.RedisService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  @Description 验证码实现类
 *  @author liuhu
 *  @Date 2021/1/5 16:37
 */
@Slf4j
@Service
public class ValidateCodeServiceImpl implements IValidateCodeService {
    @Autowired
    private RedisService redisService;

    @Autowired
    private CaptchaProperties captchaProperties;


    public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置响应头
        setHeader(response, captchaProperties.getType());
        // 前端调接口同时会传一个UUID和code UUID当作验证码的key  code为value 将code和value存到redis 当调登录接口时校验验证码
        String captchaKey = request.getParameter(CaptchaConstant.CAPTCHA_KEY);
        if(StringUtils.isBlank(captchaKey)){
            throw new AuthorizeException("验证码key不存在");
        }
        Captcha captcha = createCaptcha(captchaProperties);
        redisService.set(CaptchaConstant.CAPTCHA_KEY_PREFIX+captchaKey, StringUtils.lowerCase(captcha.text()),captchaProperties.getTime());
        // 输出图片流
        captcha.out(response.getOutputStream());
    }

    @Override
    public void check(String key,String code) throws Exception {
        if (StringUtils.isBlank(code)) {
            throw new AuthorizeException("请输入验证码");
        }
        // 获取验证码值比对
        String value = (String)redisService.get(CaptchaConstant.CAPTCHA_KEY_PREFIX+key);
        if(StringUtils.isBlank(value)){
            throw new AuthorizeException("验证码已过期");
        }
        if(!StringUtils.equals(code,value)){
            throw new AuthException("验证码不正确");
        }
    }

    /**
     * @Description 构建验证码
     * @author liuhu
     * @param captchaProperties
     * @date 2021/1/5 16:50
     * @return com.wf.captcha.base.Captcha
     */
    private Captcha createCaptcha(CaptchaProperties captchaProperties) {
        Captcha captcha = null;
        // 类型
        if(StringUtils.equals(captchaProperties.getType(), CaptchaConstant.PNG)){
            captcha = new SpecCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), captchaProperties.getLength());
        }else {
            captcha = new GifCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), captchaProperties.getLength());
        }
        // 多少为
        captcha.setCharType(captchaProperties.getCharType());
        return captcha;
    }

    /**
     * @Description 设置响应头
     * @author liuhu
     * @param response
     * @param type
     * @date 2021/1/5 16:50
     * @return void
     */
    private void setHeader(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, CaptchaConstant.GIF)) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, AuthConstants.NO_CACHE);
        response.setHeader(HttpHeaders.CACHE_CONTROL, AuthConstants.NO_CACHE);
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}
