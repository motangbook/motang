package com.motang.common.core.exception;
/**
 * @description 系统自定义异常  业务抛出
 * @author liuhu
 * @Date 2020/12/15 16:11
 */
public class BaseException extends RuntimeException {
    public BaseException(String message){
        super(message);
    }
}
