package com.motang.common.security.handler;


import com.motang.common.core.entity.CommonResponse;
import com.motang.common.core.utils.CommonUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  @Description 处理403异常
 *  @author liuhu
 *  @Date 2020/12/31 15:29
 */
public class CloudAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        CommonResponse commonResponse = new CommonResponse();
        CommonUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, commonResponse.message("没有权限访问该资源"));
    }
}
