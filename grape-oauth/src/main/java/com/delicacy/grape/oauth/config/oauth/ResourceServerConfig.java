package com.delicacy.grape.oauth.config.oauth;

import com.delicacy.grape.oauth.config.error.CustomOauthException;
import com.delicacy.grape.oauth.config.error.CustomWebResponseExceptionTranslator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {

        resources.stateless(true);
        // Invalid access token 方面异常
        OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        authenticationEntryPoint.setExceptionTranslator(new CustomWebResponseExceptionTranslator());
        resources.authenticationEntryPoint(authenticationEntryPoint);

        // access_denied 方面异常
        OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler = new OAuth2AccessDeniedHandler();
        oAuth2AccessDeniedHandler.setExceptionTranslator(new CustomWebResponseExceptionTranslator());
        resources.accessDeniedHandler(oAuth2AccessDeniedHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.logout().deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler());
        http.authorizeRequests().anyRequest().authenticated();


    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            String bearer_accesstoken = request.getHeader("Authorization");
            if (!ObjectUtils.isEmpty(bearer_accesstoken)) {
                String accessToken = bearer_accesstoken.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
                OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
                if (oAuth2AccessToken != null) {
                    log.info("【退出，access_token】:{}" , oAuth2AccessToken.getValue());
                    tokenStore.removeAccessToken(oAuth2AccessToken);
                    OAuth2RefreshToken oAuth2RefreshToken = oAuth2AccessToken.getRefreshToken();
                    tokenStore.removeRefreshToken(oAuth2RefreshToken);
                    tokenStore.removeAccessTokenUsingRefreshToken(oAuth2RefreshToken);
                }
            }
            response.setStatus(HttpServletResponse.SC_OK);
            Map map = new HashMap<>();
            map.put("success",true);
            objectMapper.writeValue(response.getOutputStream(),map);
        };
    }



}