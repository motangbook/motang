package com.motang.crawl.handler;

import com.motang.common.core.handler.BaseExceptionHandler;
import com.motang.crawl.exception.CrawlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @description 通用异常处理基类  其他微服务异常处理 需要继承基类并标准@controllerAdvance
 * @author liuhu
 * @Date 2020/12/17 14:10
 */
@ControllerAdvice
public class CrawlExceptionHandler extends BaseExceptionHandler {

    /**
     * @description 处理系统异常
     * @author liuhu
     * @param e
     * @date 2020/12/17 14:11
     * @return org.springframework.http.ResponseEntity
     */
    @ExceptionHandler(CrawlException.class)
    public ResponseEntity handlerBaseException(CrawlException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
