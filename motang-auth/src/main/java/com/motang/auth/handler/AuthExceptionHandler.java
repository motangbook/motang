package com.motang.auth.handler;


import com.motang.auth.exception.AuthorizeException;
import com.motang.common.core.handler.BaseExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  @Description 代码生成模块异常处理
 *  @author liuhu
 *  @Date 2020/6/9 11:55
 */
@RestControllerAdvice
public class AuthExceptionHandler extends BaseExceptionHandler {

    /**
     * @Description 处理auth服务异常
     * @author liuhu
     * @param e
     * @date 2021/1/5 20:38
     * @return org.springframework.http.ResponseEntity
     */
    @ExceptionHandler(AuthorizeException.class)
    public ResponseEntity<String> handlerAuthExceptionHandler(AuthorizeException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
