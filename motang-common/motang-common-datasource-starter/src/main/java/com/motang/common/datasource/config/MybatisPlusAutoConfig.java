package com.motang.common.datasource.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.motang.common.datasource.handler.BookMetaObjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @description mybatis-plus自动注入
 * @author liuhu
 * @Date 2020/12/17 10:50
 */
@Configuration
public class MybatisPlusAutoConfig {

    /**
     * @description 分页插件
     * @author liuhu
     * @date 2020/12/17 11:00
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    /**
     * @description 字段值自动注入
     * @author liuhu
     * @date 2020/12/17 11:00
     * @return com.motang.common.datasource.handler.BookMetaObjectHandler
     */
   @Bean
    public BookMetaObjectHandler bookMetaObjectHandler(){
        return new BookMetaObjectHandler();
   }
}
