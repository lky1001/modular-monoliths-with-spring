package com.tistory.lky1001.user.application.authorization.getuserauthentication;

import com.tistory.lky1001.user.application.contracts.IResult;

public class GetUserAuthenticationResult implements IResult {

    private boolean isAuthenticated;
    private String authenticationError;

    private UserAuthenticationDto userAuthenticationDto;

    public GetUserAuthenticationResult(String authenticationError) {
        this.isAuthenticated = false;
        this.authenticationError = authenticationError;
    }

    public GetUserAuthenticationResult(UserAuthenticationDto userAuthenticationDto) {
        this.isAuthenticated = true;
        this.userAuthenticationDto = userAuthenticationDto;
    }

    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    public UserAuthenticationDto getUserAuthentication() {
        return this.userAuthenticationDto;
    }
}
