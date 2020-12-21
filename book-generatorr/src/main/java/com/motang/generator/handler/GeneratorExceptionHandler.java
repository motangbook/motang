package com.motang.generator.handler;


import com.motang.common.core.handler.BaseExceptionHandler;
import com.motang.generator.exception.GeneratorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  @Description 代码生成模块异常处理
 *  @author liuhu
 *  @Date 2020/6/9 11:55
 */
@RestControllerAdvice
public class GeneratorExceptionHandler extends BaseExceptionHandler {
    @ExceptionHandler(GeneratorException.class)
    public ResponseEntity handlerAuthExceptionHandler(GeneratorException e){
        return ResponseEntity.ok(e.getMessage());
    }
}
