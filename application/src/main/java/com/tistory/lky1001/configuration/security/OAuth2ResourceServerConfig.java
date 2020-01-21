package com.tistory.lky1001.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private String resourceId;

    private TokenStore tokenStore;
    private WebResponseExceptionTranslator<OAuth2Exception> oAuth2ExceptionTranslator;

    public OAuth2ResourceServerConfig(@Value("${security.oauth2.resource.id}") String resourceId,
            TokenStore tokenStore, WebResponseExceptionTranslator<OAuth2Exception> oAuth2ExceptionTranslator) {
        this.resourceId = resourceId;
        this.tokenStore = tokenStore;
        this.oAuth2ExceptionTranslator = oAuth2ExceptionTranslator;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        final OAuth2AccessDeniedHandler handler = new OAuth2AccessDeniedHandler();
        handler.setExceptionTranslator(oAuth2ExceptionTranslator);
        return handler;
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        final OAuth2AuthenticationEntryPoint entryPoint = new OAuth2AuthenticationEntryPoint();
        entryPoint.setExceptionTranslator(oAuth2ExceptionTranslator);
        return entryPoint;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId)
                .tokenStore(tokenStore)
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions().disable()
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .authorizeRequests()
                .anyRequest().permitAll();
    }
}
