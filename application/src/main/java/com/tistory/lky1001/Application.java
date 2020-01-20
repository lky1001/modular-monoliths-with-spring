package com.tistory.lky1001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableJdbcRepositories(basePackages = "com.tistory.lky1001")
@EntityScan("com.tistory.lky1001")
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "com.tistory.lky1001")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
