package com.github.yanglong.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * package: com.github.yanglong.api <br/>
 * functional describe:
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
    public String sayHello() throws Exception{
        Thread.sleep(5000);//模拟网络问题，也可以直接关闭应用
        return name + ":" + port + " is onLine!";
    }
}
