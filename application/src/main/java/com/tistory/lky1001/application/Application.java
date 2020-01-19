package com.tistory.lky1001.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication(scanBasePackages = "com.tistory.lky1001")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
