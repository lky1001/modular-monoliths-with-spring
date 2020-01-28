package com.tistory.lky1001.configuration.security;

import com.tistory.lky1001.user.application.authorization.IPasswordManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;
    private IPasswordManager passwordManager;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, IPasswordManager passwordManager) {
        this.userDetailsService = userDetailsService;
        this.passwordManager = passwordManager;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String userId = authentication.getName();
        String password = (String) authentication.getCredentials();

        var userDetails = userDetailsService.loadUserByUsername(userId);

        if (passwordManager.matches(password, userDetails.getPassword())) {
            // todo - update last logged in
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }

        throw new InvalidGrantException(OAuth2Exception.INVALID_GRANT);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
