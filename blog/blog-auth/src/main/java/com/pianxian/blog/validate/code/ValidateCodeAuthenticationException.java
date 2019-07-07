package com.pianxian.blog.validate.code;

import org.springframework.security.core.AuthenticationException;

/*
 * 图形验证码不通过异常
 */
public class ValidateCodeAuthenticationException extends AuthenticationException {

    public ValidateCodeAuthenticationException(String msg) {
        super(msg);
    }
}
