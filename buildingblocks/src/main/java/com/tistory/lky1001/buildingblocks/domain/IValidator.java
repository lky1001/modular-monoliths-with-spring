package com.tistory.lky1001.buildingblocks.domain;

public interface IValidator {

    boolean isValid();
    int getCode();
    String getMessage();
}
