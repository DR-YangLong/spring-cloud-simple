package com.github.yanglong.service.fallback;

import com.github.yanglong.service.GetAnotherServiceStatus;
import org.springframework.stereotype.Component;

/**
 * functional describe:
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/17
 */
@Component
public class ServiceStatusFallback implements GetAnotherServiceStatus {
    @Override
    public String hello() {
        return "service down!";
    }
}
