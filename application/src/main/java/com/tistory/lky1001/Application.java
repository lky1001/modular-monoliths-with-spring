package com.tistory.lky1001;

import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.GuavaEventBus;
import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.IEventsBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public IEventsBus eventsBus() {
        return new GuavaEventBus();
    }
}
