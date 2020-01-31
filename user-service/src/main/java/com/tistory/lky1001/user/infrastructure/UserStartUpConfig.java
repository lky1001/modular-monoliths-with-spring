package com.tistory.lky1001.user.infrastructure;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import com.tistory.lky1001.buildingblocks.infrastructure.chiper.AES256Cipher;
import com.tistory.lky1001.buildingblocks.infrastructure.chiper.ICipherManager;
import com.tistory.lky1001.user.application.authorization.IPasswordManager;
import com.tistory.lky1001.user.application.authorization.PasswordManager;
import com.tistory.lky1001.user.application.configuration.commands.ICommandService;
import com.tistory.lky1001.user.application.configuration.queries.IQueryService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.Executor;
import java.util.stream.Stream;

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

    @Bean("userCommandPipeline")
    public Pipeline userCommandPipeline(ObjectProvider<Command.Handler> commandHandlers, @Qualifier("userLoggingCommandServiceMiddleware") Command.Middleware loggingCommandServiceMiddleware,
            @Qualifier("userTransactionalCommandServiceMiddleware") Command.Middleware transactionalCommandServiceMiddleware) {
        return new Pipelinr()
                .with(() -> commandHandlers.stream().filter(item -> item instanceof ICommandService))
                .with(() -> Stream.of(loggingCommandServiceMiddleware, transactionalCommandServiceMiddleware));
    }

    @Bean("userQueryPipeline")
    public Pipeline userQueryPipeline(ObjectProvider<Command.Handler> commandHandlers, @Qualifier("userTransactionalQueryServiceMiddleware") Command.Middleware transactionalQueryServiceMiddleware) {
        return new Pipelinr()
                .with(() -> commandHandlers.stream().filter(item -> item instanceof IQueryService))
                .with(() -> Stream.of(transactionalQueryServiceMiddleware));
    }

    @Bean("userNotificationPipeline")
    public Pipeline userNotificationPipeline(ObjectProvider<Notification.Handler> notificationHandlers) {
        return new Pipelinr()
                .with(notificationHandlers::stream);
    }

    @Bean(name = "userOutboxJobTaskExecutor")
    public Executor userOutboxJobTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(20);
        taskExecutor.setThreadNamePrefix("UserOutboxJobTaskExecutor-");
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean(name = "userInboxJobTaskExecutor")
    public Executor userInboxJobTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(20);
        taskExecutor.setThreadNamePrefix("UserInboxJobTaskExecutor-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
