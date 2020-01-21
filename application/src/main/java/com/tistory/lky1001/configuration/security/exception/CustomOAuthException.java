package com.tistory.lky1001.configuration.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@Getter
@JsonSerialize(using = CustomOAuthExceptionSerializer.class)
public class CustomOAuthException extends OAuth2Exception {

    private int code;

    public CustomOAuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomOAuthException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public CustomOAuthException(String msg) {
        super(msg);
    }
}
