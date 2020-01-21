package com.tistory.lky1001.user.domain.users.validator;

import com.tistory.lky1001.buildingblocks.domain.DomainValidationException;
import com.tistory.lky1001.buildingblocks.domain.IValidator;
import com.tistory.lky1001.user.application.contracts.EnumDomainMessage;

public class EmailValidator implements IValidator {

    private String email;
    private EnumDomainMessage msg;

    public EmailValidator(String email) {
        this.email = email;
    }

    @Override
    public boolean isValid() {
        this.assertEmailValid();

        return true;
    }

    private void assertEmailValid() {
        org.apache.commons.validator.routines.EmailValidator emailValidator = org.apache.commons.validator.routines.EmailValidator.getInstance(false);

        if (!emailValidator.isValid(email)) {
            throw new DomainValidationException(EnumDomainMessage.INVALID_EMAIL.getCode(), EnumDomainMessage.INVALID_EMAIL.getMsg());
        }
    }

    @Override
    public int getCode() {
        return msg.getCode();
    }

    @Override
    public String getMessage() {
        return msg.getMsg();
    }
}
