package com.motang.common.core.handler;

import com.motang.common.core.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * @description 通用异常处理基类  其他微服务异常处理 需要继承基类并标准@controllerAdvance
 * @author liuhu
 * @Date 2020/12/17 14:10
 */
public class BaseExceptionHandler {

    /**
     * @description 处理系统异常
     * @author liuhu
     * @param e
     * @date 2020/12/17 14:11
     * @return org.springframework.http.ResponseEntity
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity handlerBaseException(BaseException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    /**
     * @description 处理全局异常
     * @author liuhu
     * @param e
     * @date 2020/12/17 14:11
     * @return org.springframework.http.ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("系统异常，请联系管理员");
    }
}
