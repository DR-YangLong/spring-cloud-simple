package com.github.yanglong.consumer;

import com.github.yanglong.remote.RemoteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * package: com.github.yanglong.consumer <br/>
 * functional describe:
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/8/22
 */
@RestController
public class FeignController {

    @Autowired
    private RemoteServiceInterface remoteServiceInterface;

    @GetMapping("feign")
    public String serverInfo() {
        return remoteServiceInterface.hello();
    }
}
