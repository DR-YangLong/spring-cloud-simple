package com.github.yanglong.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * package: com.github.yanglong.consumer <br/>
 * functional describe:没有使用feign时调用远程服务
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/8/22
 */
@RestController
public class ConsumerController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/")
    public String serverInfo(){
        List<ServiceInstance> services=discoveryClient.getInstances("api-service");//远程服务的名称，配置文件中的spring.application.name，不区分大小写
        if(!CollectionUtils.isEmpty(services)){
            URI uri=services.get(0).getUri();
            if(null!=uri){
                return (new RestTemplate()).getForObject(uri+"/hello",String.class);//生产不能这么写
            }
        }
        return "no service";
    }
}
