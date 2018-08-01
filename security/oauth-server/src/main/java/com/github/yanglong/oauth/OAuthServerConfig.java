package com.github.yanglong.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * functional describe:认证服务配置
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/7/20
 */
@Configuration
@EnableAuthorizationServer
public class OAuthServerConfig extends AuthorizationServerConfigurerAdapter {
    @Value("${signingKey:default}")
    private String signingKey;
    @Autowired
    @Qualifier("customUserDetailService")
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("android")//简化模式下client_id
                .secret("{noop}android")//密码，android
                .scopes("service")//权限范围
                .authorizedGrantTypes("password", "authorization_code", "refresh_token","client_credentials")
                .accessTokenValiditySeconds(3600)
                .and()
                .withClient("webapp")
                .secret("{noop}123456")//123456
                .scopes("service")
                .authorizedGrantTypes("implicit", "password","client_credentials")
                .accessTokenValiditySeconds(3600)
                .and()
                .withClient("gateway")//简化模式下client_id
                .secret("{bcrypt}123456")//123456
                .scopes("all")//权限范围
                .authorizedGrantTypes("password", "authorization_code", "refresh_token","client_credentials")
                .accessTokenValiditySeconds(3600);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)//若无，refresh_token会有UserDetailsService is required错误
                .tokenStore(tokenStore())
                .tokenEnhancer(jwtAccessTokenConverter());//定义token存储
    }

    /**
     * 令牌存储实现，最好放入分布式缓存中，如redis等
     *
     * @return token存储实现
     */
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    /**
     * 令牌转换器，用来生成，解析token
     *
     * @return 令牌转换器
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new CustomJwtAccessTokenConverter();
        KeyStoreKeyFactory storeKeyFactory=new KeyStoreKeyFactory(new ClassPathResource("jwt_key.keystore"),"123456".toCharArray());
        KeyPair keyPair=storeKeyFactory.getKeyPair("jwt_key");
        System.out.println("private key:"+keyPair.getPrivate().toString());
        System.out.println("public key:"+keyPair.getPublic().toString());
        converter.setKeyPair(keyPair);
        return converter;
    }

}
