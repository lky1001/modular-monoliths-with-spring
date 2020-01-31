package com.tistory.lky1001.user.application.authorization;

public interface IPasswordManager {

    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
