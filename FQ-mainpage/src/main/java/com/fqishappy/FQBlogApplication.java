package com.fqishappy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fqishappy
 * @date 2024/9/10 18:25
 */

@SpringBootApplication
@MapperScan("com.fqishappy.mapper")
public class FQBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(FQBlogApplication.class, args);
    }
}
