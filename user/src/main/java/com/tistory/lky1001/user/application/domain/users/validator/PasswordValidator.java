package com.tistory.lky1001.user.application.domain.users.validator;

import com.tistory.lky1001.buildingblocks.domain.IValidator;

public class PasswordValidator implements IValidator {

    private String password;
    private String msg;

    public PasswordValidator(String password) {
        this.password = password;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
