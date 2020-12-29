package com.motang.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @description 前台门户启动器
 * @author liuhu
 * @Date 2020/12/17 10:14
 */
@SpringBootApplication
@MapperScan("com.motang.portal.mapper")
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class,args);
    }
}
