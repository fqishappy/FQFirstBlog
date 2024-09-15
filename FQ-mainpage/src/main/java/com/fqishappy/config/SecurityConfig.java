package com.fqishappy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author fqishappy
 * @date 2024/9/13 19:54
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 关闭 csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 设置为无状态
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll() // 允许匿名访问登录接口
                .anyRequest().authenticated(); // 其他请求需要认证
        http.logout().disable(); // 禁用默认的登出行为
        http.cors(); // 启用跨域
    }
}