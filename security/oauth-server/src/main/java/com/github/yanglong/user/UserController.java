package com.github.yanglong.user;

import com.github.yanglong.oauth.CustomUserDetailService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * functional describe:用户信息控制器，供资源服务器认证时拉取用户权限信息，对应Resource Server中的
 * <p>
 * resource:
 * user-info-uri: http://eureka-first:11112/user/info
 * </p>
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/1
 */
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 从principal获取到用户名，使用用户名获取用户及权限信息返回
     *
     * @param principal 用户principal
     * @return
     */
    @RequestMapping("info")
    public UserDetails getUser(Principal principal) {
        return new CustomUserDetailService.CustomUserDetail(principal.getName(), "123456");
    }
}
