package com.tistory.lky1001.modules.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    private String email;
    private String password;
    private String name;
}
