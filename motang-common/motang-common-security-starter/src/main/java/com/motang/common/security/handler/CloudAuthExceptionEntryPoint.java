package com.motang.common.security.handler;


import com.motang.common.core.entity.CommonResponse;
import com.motang.common.core.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  @Description 处理401
 *  @author liuhu
 *  @Date 2020/12/31 15:29
 */
@Slf4j
public class CloudAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String requestUri = request.getRequestURI();
        String message = "访问令牌不合法";
        log.error("客户端访问{}请求失败: {}", requestUri, message, authException);
        CommonUtil.makeJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED, new CommonResponse().message(message));
    }
}
