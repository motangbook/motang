package com.motang.common.security.config;

import com.motang.common.core.constant.CommonConstants;
import com.motang.common.security.handler.CloudAccessDeniedHandler;
import com.motang.common.security.handler.CloudAuthExceptionEntryPoint;
import com.motang.common.security.utils.AccountUtil;
import feign.RequestInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *  @Description 为oauth2 认证 做一些公共配置自动注入
 *  @author liuhu
 *  @Date 2020/12/31 15:39
 */
@Configuration
public class CloudResourceServerAutoConfigurer {


    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public CloudAccessDeniedHandler accessDeniedHandler() {
        return new CloudAccessDeniedHandler();
    }

    /**
     * @Description 注册403 bean到spring
     * @author liuhu
     * @date 2020/12/31 15:41
     * @return com.motang.common.security.handler.CloudAuthExceptionEntryPoint
     */
    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public CloudAuthExceptionEntryPoint authenticationEntryPoint() {
        return new CloudAuthExceptionEntryPoint();
    }

    /**
     * @Description spring hash加密
     * @author liuhu
     * @date 2020/12/31 15:41
     * @return org.springframework.security.crypto.password.PasswordEncoder
     */
    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @Description 服务调用时  feign 不会主动帮我们转发token 手动拦截放入请求头
     * @author liuhu
     * @param
     * @date 2020/12/31 15:43
     * @return feign.RequestInterceptor
     */
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            String authorizationToken = AccountUtil.getCurrentTokenValue();
            if (StringUtils.isNotBlank(authorizationToken)) {
                requestTemplate.header(HttpHeaders.AUTHORIZATION, CommonConstants.OAUTH2_TOKEN_TYPE + authorizationToken);
            }
        };
    }
}
