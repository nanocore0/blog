package com.pianxian.blog.validate.code;

import com.pianxian.blog.validate.code.image.ImageCodeFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 校验码 配置类
 */
@Configuration
@EnableConfigurationProperties(ValidateCodeProperties.class)
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    ImageCodeFilter imageCodeFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 允许获取图像验证码
        http.authorizeRequests().antMatchers("/code/image").permitAll();
        // 图形验证码过滤器加在拦截器链上
        if (imageCodeFilter != null) {
            logger.info("图形验证码生效");
            http.addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }
}
