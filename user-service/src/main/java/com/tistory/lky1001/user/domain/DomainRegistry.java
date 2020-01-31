package com.tistory.lky1001.user.domain;

import com.tistory.lky1001.buildingblocks.infrastructure.chiper.ICipherManager;
import com.tistory.lky1001.user.application.authorization.IPasswordManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DomainRegistry implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static IPasswordManager passwordManager() {
        return (IPasswordManager) applicationContext.getBean("passwordManager");
    }

    public static ICipherManager cipherManager() {
        return (ICipherManager) applicationContext.getBean("cipherManager");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (DomainRegistry.applicationContext == null) {
            DomainRegistry.applicationContext = applicationContext;
        }
    }
}
