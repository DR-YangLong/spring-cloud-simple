package com.github.yanglong.security;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;
import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import javax.servlet.*;
import java.io.IOException;

/**
 * functional describe:
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/1
 */
@EnableConfigurationProperties
@EnableOAuth2Sso
@Configuration
public class OAuthConfig {
    public static final Logger logger = LoggerFactory.getLogger(OAuthConfig.class);
    /**
     *
     */
    private static final HystrixRequestVariableDefault<Authentication> authentication = new HystrixRequestVariableDefault<>();

    /**
     * @return
     */
    public static HystrixRequestVariableDefault<Authentication> getInstance() {
        return authentication;
    }

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            Authentication token = OAuthConfig.getInstance().get();
            if (null != token) {
                logger.debug("Get Authorization from Hystrix:" + token);
                requestTemplate.header(OAuth2FeignRequestInterceptor.AUTHORIZATION, OAuth2FeignRequestInterceptor.BEARER + " " + ((OAuth2AuthenticationDetails) token.getDetails()).getTokenValue());
            } else {
                logger.debug("Can't Get Authorization.");
            }
        };
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HystrixFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(0);
        return registrationBean;
    }


    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
    }


    public static class HystrixFilter implements Filter{
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HystrixRequestContext.initializeContext();
            SecurityContext securityContext = SecurityContextHolder.getContext();
            if (null != securityContext) {
                Authentication authentication = securityContext.getAuthentication();
                OAuthConfig.getInstance().set(authentication);
            }
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {

        }
    }
}
