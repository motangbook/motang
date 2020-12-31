package com.motang.common.security.config;

import com.motang.common.security.handler.CloudAccessDeniedHandler;
import com.motang.common.security.handler.CloudAuthExceptionEntryPoint;
import com.motang.common.security.properties.CloudSecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
/**
 *  @Description 资源服务器
 *  @author liuhu
 *  @Date 2020/12/29 13:40
 */
@ConditionalOnProperty(value = "motang.security.enable", havingValue = "true", matchIfMissing = true)
@Configuration
@EnableResourceServer
@EnableConfigurationProperties({CloudSecurityProperties.class})
public class CloudResourceServerConfigurer  implements ResourceServerConfigurer {

    public final static String COMMA=",";

    @Autowired
    private CloudSecurityProperties properties;

    @Autowired
    private CloudAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private CloudAuthExceptionEntryPoint authExceptionEntryPoint;

    /**
     * @Description 对于请求资源401  403问题处理
     * @author liuhu
     * @param configurer
     * @date 2020/12/29 13:50
     * @return void
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer configurer) throws Exception {
          configurer.authenticationEntryPoint(authExceptionEntryPoint)
                  .accessDeniedHandler(accessDeniedHandler);
    }

    /**
     * @Description 资源服务器访问资源控制
     * @author liuhu
     * @param httpSecurity
     * @date 2020/12/31 15:33
     * @return void
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        String[] anonUrls = new String[]{};
        if(StringUtils.isNotBlank(properties.getAnonUrls())){
            anonUrls = properties.getAnonUrls().split(COMMA);
        }
            httpSecurity.authorizeRequests()
                    .antMatchers(anonUrls)
                    .permitAll()
                    .anyRequest()
                    .authenticated();
    }
}
