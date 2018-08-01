package com.github.yanglong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;

/**
 * 认证服务器
 */
@SpringBootApplication
@EnableEurekaClient
@PropertySource("classpath:application.yml")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
