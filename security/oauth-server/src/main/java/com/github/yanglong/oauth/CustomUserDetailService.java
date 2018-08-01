package com.github.yanglong.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * functional describe:spring security UserDetailsService实现，用来加载用户及用户权限信息。
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/7/23
 */
@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //从数据源查询获取用户，如果无法获取用户，需要手动抛出异常
        String pwd=passwordEncoder.encode("123456");
        CustomUserDetail user=new CustomUserDetail(s,pwd);
        return user;
    }

    /**
     * 自定义用户实现
     */
    public static class CustomUserDetail implements UserDetails {
        private String userName;
        private String password;

        public CustomUserDetail(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<String> authorizations = Arrays.asList("select", "update", "delete", "alter", "insert");
            Collection<SimpleGrantedAuthority> authorities = authorizations.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return userName;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
