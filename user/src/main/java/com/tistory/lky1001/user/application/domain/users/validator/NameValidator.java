package com.tistory.lky1001.user.application.domain.users.validator;

import com.tistory.lky1001.buildingblocks.domain.IValidator;

public class NameValidator implements IValidator {

    private String name;
    private String msg;

    public NameValidator(String name) {
        this.name = name;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
