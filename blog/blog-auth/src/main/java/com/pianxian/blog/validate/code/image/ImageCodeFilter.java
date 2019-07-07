package com.pianxian.blog.validate.code.image;

import com.pianxian.blog.handler.MyAuthenticationFaulureHandler;
import com.pianxian.blog.validate.code.ValidateCodeAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/*
 * 图形验证码过滤器
 */
public class ImageCodeFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MyAuthenticationFaulureHandler authenticationFaulureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("进入图形验证");
        if (request.getRequestURI().contains("/login") && request.getMethod().equalsIgnoreCase("post")) {
            try {
                // 拿到用户输入的验证码
                String code = request.getParameter("imageCode");
                // 拿到系统生成的验证码
                ImageCode imageCode = (ImageCode) request.getSession().getAttribute("SESSION_KEY_FOR_CODE_IMAGE");
                // 校验验证码
                if (code == null) {
                    throw new ValidateCodeAuthenticationException("验证码必须输入");
                }
                if (imageCode == null) {
                    throw new ValidateCodeAuthenticationException("验证码不存在");
                }
                if (LocalDateTime.now().isAfter(imageCode.getExpireTime())) {
                    throw new ValidateCodeAuthenticationException("验证码过期   ");
                }
                if (!code.trim().equalsIgnoreCase(imageCode.getCode())) {
                    throw new ValidateCodeAuthenticationException("验证码不匹配");
                }
                // 移除 session 中的验证码
                request.getSession().removeAttribute("SESSION_KEY_FOR_CODE_IMAGE");
            } catch (AuthenticationException e) {
                // 验证不通过，交给自定义的 验证失败处理器 处理
                authenticationFaulureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
