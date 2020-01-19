package com.tistory.lky1001.user.application.domain.users.validator;

import com.tistory.lky1001.buildingblocks.domain.IValidator;
import org.springframework.util.StringUtils;

public class EmailValidator implements IValidator {

    private String email;
    private String msg;

    public EmailValidator(String email) {
        this.email = email;
    }

    @Override
    public boolean isValid() {
        if (StringUtils.isEmpty(email)) {
            msg = "Email is required.";
            return false;
        }

        return true;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
