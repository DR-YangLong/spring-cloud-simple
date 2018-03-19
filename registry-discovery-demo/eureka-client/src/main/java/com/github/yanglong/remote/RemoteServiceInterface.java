package com.github.yanglong.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * package: com.github.yanglong.remote <br/>
 * functional describe:使用feign组件会自动使用ribbon进行客户端负载均衡
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/8/22
 */
@Component
@FeignClient(name = "api-service")
public interface RemoteServiceInterface {

    @RequestMapping(value = "/hello")
    String hello();
}
