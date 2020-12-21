package com.motang.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *  @Description  security web层配置
 *  @author liuhu
 *  @Date 2020/5/13 9:09
 */
@Order(2)
@EnableWebSecurity
@RequiredArgsConstructor
public class RainbowSecurityConfigure extends WebSecurityConfigurerAdapter {

    /**
     * @Description oauth2.0密码模式认证器
     * @author liuhu
     * @createTime 2020-05-13 09:11:11
     * @param
     * @return org.springframework.security.authentication.AuthenticationManager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().and()
                .requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/auth/social/**")
                .permitAll()
                .antMatchers("/oauth/**")
                .permitAll()
                .antMatchers("/*")
                .authenticated();

    }
}
