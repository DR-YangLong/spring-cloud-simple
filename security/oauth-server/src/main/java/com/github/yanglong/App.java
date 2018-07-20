package com.github.yanglong;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 认证服务器
 */
@SpringCloudApplication
@EnableFeignClients
@EnableAuthorizationServer
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
