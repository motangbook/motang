package com.motang.common.doc.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description swagger属性
 * @author liuhu
 * @Date 2020/12/16 21:29
 */
@ConfigurationProperties(prefix = "motang.swagger")
public class SwaggerProperties {
    /**
     * 是否开启doc功能
     */
    private Boolean enable;
    /**
     * 接口扫描路径，如Controller路径
     */
    private String basePackage;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档描述
     */
    private String description;
    /**
     * 联系方式：姓名
     */
    private String name;
    /**
     * 联系方式：个人网站url
     */
    private String url;
    /**
     * 联系方式：邮箱
     */
    private String email;
    /**
     * 协议
     */
    private String license;
    /**
     * 协议地址
     */
    private String licenseUrl;
    /**
     * 版本
     */
    private String version;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
