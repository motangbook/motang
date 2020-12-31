package com.motang.auth.config;

import com.motang.auth.constant.AuthConstants;
import com.motang.auth.filter.ValidateCaptchaFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *  @Description  security web层配置
 *  @author liuhu
 *  @Date 2020/5/13 9:09
 */
@Order(2)
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private ValidateCaptchaFilter validateCaptchaFilter;

    @Autowired
    private AuthUserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @Description oauth2.0密码模式认证器
     * @author liuhu
     * @createTime 2020-05-13 09:11:11
     * @return org.springframework.security.authentication.AuthenticationManager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // validateCaptchaFilter 验证码过滤器在登录之前校验
        http.addFilterBefore(validateCaptchaFilter,UsernamePasswordAuthenticationFilter.class).formLogin()
                .and()
                .requestMatchers()
                .antMatchers(AuthConstants.AllUrl)
                .and()
                .authorizeRequests()
                .antMatchers(AuthConstants.OAUTH_URL)
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    /**
     * @Description 指定校验类 和校验密码
     * @author liuhu
     * @param auth
     * @date 2020/12/31 16:40
     * @return void
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
}
