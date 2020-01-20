package com.tistory.lky1001.buildingblocks.infrastructure.chiper;

public interface ICipherManager {

    String encode(String rawStr) throws EncryptException;
    String decode(String encodedStr) throws DecryptException;
}
