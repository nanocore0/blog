package com.pianxian.blog.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证失败处理器
 */
@Component
public class MyAuthenticationFaulureHandler implements AuthenticationFailureHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        logger.info("登录失败");
        Map<String, Object> map = new HashMap<>();
        map.put("message", exception.getMessage());
        // 返回错误信息
        String json = objectMapper.writeValueAsString(map);
        response.setContentType("text/json;charset=utf-8");
        response.setStatus(500);
        response.getWriter().write(json);
    }
}
