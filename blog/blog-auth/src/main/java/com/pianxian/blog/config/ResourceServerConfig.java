package com.pianxian.blog.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 资源服务器配置
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 允许跨域访问
        http.cors();
        // 权限控制
        http.authorizeRequests()
                .antMatchers("/images/**", "/actuator/**").permitAll()
                .antMatchers("/hello", "/login", "/register").permitAll()
                .antMatchers("/blogs", "/blogs/**", "/comments/**", "/catalogs/**", "/tags/**", "/search").permitAll()
                .antMatchers(HttpMethod.GET, "/blog/*", "/user/**", "/catalog/*").permitAll()
                .anyRequest().authenticated();
        // 登录
        http.formLogin()
                .loginPage("/login").successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
                .and()
                .logout().logoutSuccessUrl("/login");
        // 关闭 csrf
        http.csrf().disable();
    }
}
