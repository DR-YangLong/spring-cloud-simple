package com.github.yanglong.api;

import com.github.yanglong.service.GetAnotherServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
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
    @Autowired
    private GetAnotherServiceStatus serviceStatus;

    @GetMapping("/hello")
    public String sayHello() {
        String status;
        if(!"api-service-b".equals(name)) {
            status = serviceStatus.hello();
            System.out.println("从api-service-b返回："+status);
        }else{
            status="api-service-b不进行调用";
        }
        return name + ":" + port + " is onLine!Get another service:" + status;
    }


    @GetMapping("/api/hello")
    public String hello() {
        String status;
        if(!"api-service-b".equals(name)) {
            status = serviceStatus.hello();
            System.out.println("从api-service-b返回："+status);
        }else{
            status="api-service-b不进行调用";
        }
        return name + ":" + port + " is onLine!Get another service:" + status;
    }

    /**
     * 获取认证信息
     *
     * @return
     */
    private String getAuthorizationToken() {
        String token = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getClass().isAssignableFrom(OAuth2Authentication.class)) {
            OAuth2Authentication auth = (OAuth2Authentication) authentication;
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
            token = details.getTokenValue();
//            token = details.getTokenType() + " " + token;
        }
        return token;
    }
}
