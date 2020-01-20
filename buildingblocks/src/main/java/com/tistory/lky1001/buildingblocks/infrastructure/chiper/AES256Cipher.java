package com.tistory.lky1001.buildingblocks.infrastructure.chiper;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES256Cipher implements ICipherManager {

    private String salt;

    private String iv = "";

    public AES256Cipher(String salt) {
        this.salt = salt;
        this.iv = salt.substring(0, 16);
    }

    @Override
    public String encode(String rawStr) throws EncryptException {
        if (rawStr == null  || "".equalsIgnoreCase(rawStr)) {
            return rawStr;
        }

        byte[] keyData = salt.getBytes();
        SecretKey secureKey = new SecretKeySpec(keyData, "AES");
        Cipher c;

        try {
            c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(iv.getBytes()));

            byte[] encrypted = c.doFinal(rawStr.getBytes("UTF-8"));

            return new String(Base64.getEncoder().encode(encrypted));
        } catch (Exception e) {
            throw new EncryptException();
        }
    }

    @Override
    public String decode(String encodedStr) throws DecryptException {
        if (encodedStr == null  || "".equalsIgnoreCase(encodedStr)) {
            return encodedStr;
        }

        byte[] keyData = salt.getBytes();
        SecretKey secureKey = new SecretKeySpec(keyData, "AES");
        Cipher c;

        try {
            c = Cipher.getInstance("AES/CBC/PKCS5Padding");

            c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(iv.getBytes("UTF-8")));

            byte[] byteStr = Base64.getDecoder().decode(encodedStr.getBytes());

            return new String(c.doFinal(byteStr), "UTF-8");
        } catch (Exception e) {
            throw new DecryptException();
        }
    }
}
