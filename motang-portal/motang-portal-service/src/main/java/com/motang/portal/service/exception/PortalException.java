package com.motang.portal.service.exception;
/**
 * @description 门户服务异常
 * @author liuhu
 * @Date 2020/12/17 14:13
 */
public class PortalException extends RuntimeException {
    public PortalException(String message){
        super(message);
    }
}