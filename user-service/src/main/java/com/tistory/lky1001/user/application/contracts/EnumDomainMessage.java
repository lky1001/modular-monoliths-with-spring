package com.tistory.lky1001.user.application.contracts;

public enum EnumDomainMessage {

    SUCCESS(1, "SUCCESS"),

    // create user
    INVALID_EMAIL(-11001, "Invalid email."),
    NAME_REQUIRED(-11002, "Name is required."),
    EMAIL_ALREADY_EXIST(-11003, "Email is already exist."),
    SAME_PASSWORD(-11004, "Required not same password."),
    PASSWORD_WEAK(-11005, "The password must be stronger."),
    NAME_LENGTH(-11006, "Required name length 2~10."),
    ;

    private int code;
    private String msg;

    EnumDomainMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
