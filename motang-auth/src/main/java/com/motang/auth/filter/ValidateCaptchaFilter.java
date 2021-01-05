package com.motang.auth.filter;

import com.motang.auth.constant.AuthConstants;
import com.motang.auth.service.IValidateCodeService;
import com.motang.common.core.entity.CommonResponse;
import com.motang.common.core.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *  @Description 验证码过滤器
 *  @author liuhu
 *  @Date 2020/12/29 10:56
 */
@Component
@Slf4j
public class ValidateCaptchaFilter extends OncePerRequestFilter {

    @Autowired
    private IValidateCodeService validateCodeService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        RequestMatcher matcher = new AntPathRequestMatcher(AuthConstants.OAUTH_TOKEN, HttpMethod.POST.toString());
        // 1.判断是否是登录时获取token接口
        if (matcher.matches(request)  &&
                StringUtils.equalsIgnoreCase(request.getParameter(AuthConstants.GRANT_TYPE), AuthConstants.PASSWORD)) {
        // 2.校验验证码是否正确
            try {
                validateCode(request);
            } catch (Exception e) {
                CommonResponse commonResponse = new CommonResponse();
                CommonUtil.makeFailureResponse(response, commonResponse.message(e.getMessage()));
                log.error(e.getMessage(), e);
            }
        }
        filterChain.doFilter(request, response);

    }

    /**
     * @Description 校验用户名和密码
     * @author liuhu
     * @param request
     * @date 2021/1/5 16:30
     * @return void
     */
    private void validateCode(HttpServletRequest request) throws Exception {
        String code = request.getParameter(AuthConstants.VALIDATE_CODE_CODE);
        String key = request.getParameter(AuthConstants.VALIDATE_CODE_KEY);
        validateCodeService.check(key, code);
    }
}
