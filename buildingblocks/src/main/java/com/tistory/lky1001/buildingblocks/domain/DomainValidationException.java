package com.tistory.lky1001.buildingblocks.domain;

public class DomainValidationException extends RuntimeException {

    private int code;
    private String msg;

    public DomainValidationException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public DomainValidationException(IValidator validator) {
        this.code = validator.getCode();
        this.msg = validator.getMessage();
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.msg;
    }
}
