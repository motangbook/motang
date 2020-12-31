package com.motang.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description 授权微服务
 * @author liuhu
 * @Date 2020/12/17 15:17
 */
@SpringBootApplication
@MapperScan("com.motang.auth.mapper")
//@EnableCloudResourceServer
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }
}
