package com.pianxian.blog.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码处理器接口
 */
public interface ValidateCodeProcessor {

    /**
     * 将验证码放入 session 的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     */
    void validate(ServletWebRequest servletWebRequest);
}
