package com.delicacy.grape.oauth.config.error;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@Getter
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {

    private String oAuth2ErrorCode;

    private int httpErrorCode;

    public CustomOauthException(String msg, String oAuth2ErrorCode, int httpErrorCode) {
        super(msg);
        this.oAuth2ErrorCode = oAuth2ErrorCode;
        this.httpErrorCode = httpErrorCode;
    }
}