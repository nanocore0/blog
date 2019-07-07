package com.pianxian.blog.validate.code;

import com.pianxian.blog.validate.code.image.ImageCodeFilter;
import com.pianxian.blog.validate.code.image.SimpleImageCodeGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    /**
     * 图形校验码生成器
     * 当没有其他图形校验码生成器时生效
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        SimpleImageCodeGenerator imageCodeGenerator = new SimpleImageCodeGenerator();
        return imageCodeGenerator;
    }

    /**
     * 图形验证码过滤器，当配置了启用时生效
     */
    @Bean
    @ConditionalOnProperty(prefix = "blog.validate.code", value = "enable", havingValue = "true")
    public ImageCodeFilter imageCodeFilter() {
        return new ImageCodeFilter();
    }
}
