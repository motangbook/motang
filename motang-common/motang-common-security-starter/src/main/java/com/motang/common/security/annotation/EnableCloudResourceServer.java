package com.motang.common.security.annotation;

import com.motang.common.security.config.CloudResourceServerConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *  @Description 自定义注解  在其他微服务启动类加上注解 标注为资源服务器
 *  @author liuhu
 *  @Date 2020/12/31 15:34
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CloudResourceServerConfigurer.class)
public @interface EnableCloudResourceServer {
}
