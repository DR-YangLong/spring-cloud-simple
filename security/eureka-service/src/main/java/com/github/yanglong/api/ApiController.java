package com.github.yanglong.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * package: com.github.yanglong.api <br/>
 * functional describe: 服务测试接口，返回当前服务名和端口
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/8/22
 */
@RestController
public class ApiController {
    @Value("${server.port}")
    private String port;
    @Value("${spring.application.name}")
    private String name;

    @GetMapping("/hello")
    public String sayHello() {
        return name + ":" + port + " is onLine!";
    }
}
