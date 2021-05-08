package com.delicacy.grape.oauth.config.error;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;

/**
 * @author yutao
 * @create 2020-05-19 10:38
 **/
public class CustomWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        ResponseEntity<OAuth2Exception> translate = super.translate(e);
        OAuth2Exception body = translate.getBody();

        CustomOauthException customOauthException = new CustomOauthException(body.getMessage(),body.getOAuth2ErrorCode(),body.getHttpErrorCode());
        ResponseEntity<OAuth2Exception> response = new ResponseEntity<>(customOauthException, translate.getHeaders(),
                translate.getStatusCode());
        return response;
    }

}
