package com.pianxian.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class BlogManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogManageApplication.class, args);
    }

}
