package com.motang.crawl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 *  @Description 爬虫启动类
 *  @author liuhu
 *  @Date 2021/1/5 21:26
 */
@SpringBootApplication
@MapperScan("com.motang.crawl.mapper")
public class CrawlApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrawlApplication.class,args);
    }
}
