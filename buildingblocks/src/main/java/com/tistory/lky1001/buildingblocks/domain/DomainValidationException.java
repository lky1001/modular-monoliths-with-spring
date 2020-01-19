package com.tistory.lky1001.buildingblocks.domain;

public class DomainValidationException extends RuntimeException {

    public DomainValidationException(String msg) {
        super(msg);
    }
}
