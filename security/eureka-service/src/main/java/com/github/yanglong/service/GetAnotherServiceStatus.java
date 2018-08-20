package com.github.yanglong.service;

import com.github.yanglong.service.fallback.ServiceStatusFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * functional describe:获取其他服务状态
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/17
 */
@FeignClient(value = "api-service-b",fallback = ServiceStatusFallback.class)
public interface GetAnotherServiceStatus {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String hello();
}
