package com.github.yanglong;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Hello world!
 */
@SpringCloudApplication
@EnableHystrixDashboard
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
