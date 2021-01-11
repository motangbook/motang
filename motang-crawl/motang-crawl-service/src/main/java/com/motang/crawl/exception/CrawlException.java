package com.motang.crawl.exception;
/**
 * @description 爬虫服务异常
 * @author liuhu
 * @Date 2020/12/17 14:13
 */
public class CrawlException extends RuntimeException {
    public CrawlException(String message){
        super(message);
    }
}