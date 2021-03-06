package com.motang.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description 代码生成器启动类
 * @author liuhu
 * @Date 2020/12/21 16:30
 */
@SpringBootApplication
@MapperScan("com.motang.generator.mapper")
public class GeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class,args);
    }
}
