package com.pianxian.blog.validate.code.image;

import com.pianxian.blog.validate.code.ValidateCodeProcessor;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图形验证码处理器
 */
public class ImageCodeProcessor implements ValidateCodeProcessor {

    @Override
    public void create(ServletWebRequest request) throws Exception {

    }

    @Override
    public void validate(ServletWebRequest servletWebRequest) {

    }
}
