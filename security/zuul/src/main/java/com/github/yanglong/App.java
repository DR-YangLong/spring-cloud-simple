package com.github.yanglong;

import com.github.yanglong.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * zuul作为一个资源服务器
 */
@EnableZuulProxy
@SpringCloudApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    //@Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
}
