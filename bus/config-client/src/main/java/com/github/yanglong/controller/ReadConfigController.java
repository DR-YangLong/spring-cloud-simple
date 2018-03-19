package com.github.yanglong.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * package: com.github.yanglong.controller <br/>
 * functional describe:
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/8/22
 */
@RestController
@RefreshScope
public class ReadConfigController {
    @Value("${common}")
    private String common;
    @Value("${hello}")
    private String hello;

    @GetMapping({"/", "index"})
    public String showProfile() {
        return common + ":" + hello;
    }
}
