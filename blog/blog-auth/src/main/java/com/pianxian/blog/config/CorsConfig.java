package com.pianxian.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS 配置，允许跨域访问
 */
@Configuration
public class CorsConfig {

    // 注入 WebMvcConfigurer，但不要使用 @EnableWebMvc，保留自动配置
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                    .allowedHeaders("*")
                    .maxAge(3600L);
            }
        };
    }

    // 注入了 WebMvcConfigurationSupport，Spring MVC 自动配置将不会起作用
    /*@Bean
    public WebMvcConfigurationSupport webMvcConfigurationSupport() {
        return new WebMvcConfigurationSupport() {
            @Override
            protected void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowCredentials(true)
                    .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS");
            }
        };
    }*/

}
