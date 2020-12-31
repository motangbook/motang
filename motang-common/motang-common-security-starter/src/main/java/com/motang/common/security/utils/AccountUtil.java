package com.motang.common.security.utils;


import com.motang.common.core.entity.CloudAuthorizeUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
/**
 *  @Description 账户相关工具类
 *  @author liuhu
 *  @Date 2020/12/31 15:51
 */
public class AccountUtil {

    /**
     * @Description 获得当前请求token
     * @author liuhu
     * @date 2020/12/31 15:51
     * @return java.lang.String
     */
    public static String getCurrentTokenValue() {
        try {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) getOauth2Authentication().getDetails();
            return details.getTokenValue();
        } catch (Exception ignore) {
            return null;
        }
    }

    /**
     * @Description 获得当前登录用户名
     * @author liuhu
     * @date 2020/12/31 15:54
     * @return java.lang.String
     */
    public static String getCurrentUsername() {
        Object principal = getOauth2Authentication().getPrincipal();
        if (principal instanceof CloudAuthorizeUser) {
            return ((CloudAuthorizeUser) principal).getUsername();
        }
        return (String) getOauth2Authentication().getPrincipal();
    }


    private static OAuth2Authentication getOauth2Authentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (OAuth2Authentication) authentication;
    }



}