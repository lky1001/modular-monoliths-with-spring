package com.tistory.lky1001.sns.application.contracts;

public enum EnumDomainMessage {

    SUCCESS(1, "SUCCESS"),

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
