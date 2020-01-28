package com.tistory.lky1001.user.application.users.createuser;

import com.tistory.lky1001.user.application.contracts.AbstractCommand;

public class CreateUserCommand extends AbstractCommand<CreateUserResult> {

    private String email;
    private String password;
    private String name;

    public
    CreateUserCommand(String email, String password, String name) {
        super();
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }
}
