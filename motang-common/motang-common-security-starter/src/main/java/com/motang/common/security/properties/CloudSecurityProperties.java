package com.motang.common.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "motang.security")
public class CloudSecurityProperties {


    /**是否开启微服务网关*/
    private boolean enable;

    /**资源服务器免认证url 多个逗号拼接*/
    private String anonUrls;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getAnonUrls() {
        return anonUrls;
    }

    public void setAnonUrls(String anonUrls) {
        this.anonUrls = anonUrls;
    }
}
