package com.tistory.lky1001.user.domain.users.validator;

import com.tistory.lky1001.buildingblocks.domain.DomainValidationException;
import com.tistory.lky1001.user.application.contracts.EnumDomainMessage;
import com.tistory.lky1001.buildingblocks.domain.IValidator;
import org.springframework.util.StringUtils;

public class PasswordValidator implements IValidator {

    private static final int MIN_PASSWORD_LENGTH = 8;

    private String currentPassword;
    private String changedPassword;
    private EnumDomainMessage msg;

    public PasswordValidator(String currentPassword, String changedPassword) {
        this.currentPassword = currentPassword;
        this.changedPassword = changedPassword;
    }

    @Override
    public boolean isValid() {
        this.assertPasswordNotSame();
        this.assertPasswordNotWeak();

        return true;
    }

    private void assertPasswordNotSame() {
        if (this.currentPassword.equals(this.changedPassword)) {
            throw new DomainValidationException(EnumDomainMessage.SAME_PASSWORD.getCode(), EnumDomainMessage.SAME_PASSWORD.getMsg());
        }
    }

    private void assertPasswordNotWeak() {
        if (this.changedPassword == null || this.changedPassword.length() < MIN_PASSWORD_LENGTH) {
            throw new DomainValidationException(EnumDomainMessage.PASSWORD_WEAK.getCode(), EnumDomainMessage.PASSWORD_WEAK.getMsg());
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
