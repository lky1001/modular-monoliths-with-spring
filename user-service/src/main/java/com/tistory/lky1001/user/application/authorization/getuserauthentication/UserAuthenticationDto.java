package com.tistory.lky1001.user.application.authorization.getuserauthentication;

import java.util.List;

public class UserAuthenticationDto {

    private long userId;
    private String email;
    private String password;
    private List<RoleDto> roles;

    public UserAuthenticationDto(long userId, String email, String password, List<RoleDto> roles) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }
}
