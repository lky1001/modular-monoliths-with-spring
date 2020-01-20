package com.tistory.lky1001.user.infrastructure;

import com.tistory.lky1001.buildingblocks.infrastructure.chiper.AES256Cipher;
import com.tistory.lky1001.buildingblocks.infrastructure.chiper.ICipherManager;
import com.tistory.lky1001.user.application.authorization.IPasswordManager;
import com.tistory.lky1001.user.application.authorization.PasswordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserStartUpConfig {

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean("passwordManager")
    public IPasswordManager passwordManager(@Autowired PasswordEncoder passwordEncoder) {
        return new PasswordManager(passwordEncoder);
    }

    @Bean("cipherManager")
    public ICipherManager cipherManager(@Value("${security.salt}") String salt) {
        return new AES256Cipher(salt);
    }
}
