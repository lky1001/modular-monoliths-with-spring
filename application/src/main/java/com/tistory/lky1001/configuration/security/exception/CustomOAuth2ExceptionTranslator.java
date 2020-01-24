package com.tistory.lky1001.configuration.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import javax.security.sasl.AuthenticationException;

public class CustomOAuth2ExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        if (e instanceof InvalidGrantException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new CustomOAuthException(HttpStatus.UNAUTHORIZED.value(), OAuth2Exception.INVALID_GRANT));
        } else if (e instanceof BadCredentialsException || e instanceof InsufficientAuthenticationException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new CustomOAuthException(HttpStatus.UNAUTHORIZED.value(), OAuth2Exception.INVALID_TOKEN));
        } else if (e instanceof CustomOAuthException) {
            return ResponseEntity
                    .status(((CustomOAuthException) e).getCode())
                    .body((CustomOAuthException) e);
        } else if (e instanceof AuthenticationException || e instanceof OAuth2Exception) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new CustomOAuthException(HttpStatus.UNAUTHORIZED.value(), OAuth2Exception.UNAUTHORIZED_CLIENT));
        } else if (e instanceof AccessDeniedException) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new CustomOAuthException(HttpStatus.FORBIDDEN.value(), OAuth2Exception.ACCESS_DENIED));
        } else {
            throw e;
        }
    }
}
