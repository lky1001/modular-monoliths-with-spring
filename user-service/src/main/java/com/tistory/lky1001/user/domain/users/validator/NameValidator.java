package com.tistory.lky1001.user.domain.users.validator;

import com.tistory.lky1001.buildingblocks.domain.DomainValidationException;
import com.tistory.lky1001.buildingblocks.domain.IValidator;
import com.tistory.lky1001.user.application.contracts.EnumDomainMessage;

public class NameValidator implements IValidator {

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 20;

    private String name;
    private EnumDomainMessage msg;

    public NameValidator(String name) {
        this.name = name;
    }

    @Override
    public boolean isValid() {
        this.assertNameLength();

        return true;
    }

    private void assertNameLength() {
        if (name == null || "".equals(name)) {
            throw new DomainValidationException(EnumDomainMessage.NAME_REQUIRED.getCode(), EnumDomainMessage.NAME_REQUIRED.getMsg());
        }

        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new DomainValidationException(EnumDomainMessage.NAME_LENGTH.getCode(), EnumDomainMessage.NAME_LENGTH.getMsg());
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
