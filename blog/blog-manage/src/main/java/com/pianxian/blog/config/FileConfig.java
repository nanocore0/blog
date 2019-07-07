package com.pianxian.blog.config;

import com.pianxian.blog.file.DefaultFileService;
import com.pianxian.blog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties(FileUploadProperties.class)
public class FileConfig {

    @Autowired
    FileUploadProperties fileUploadProperties;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/images/**").addResourceLocations("file:" + fileUploadProperties.getPath());
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean(FileService.class)
    public FileService defaultFileService() {
        DefaultFileService defaultFileService = new DefaultFileService();
        return defaultFileService;
    }
}
