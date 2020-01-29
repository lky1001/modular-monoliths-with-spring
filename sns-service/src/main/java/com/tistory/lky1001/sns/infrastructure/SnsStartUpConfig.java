package com.tistory.lky1001.sns.infrastructure;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import com.tistory.lky1001.sns.application.configuration.commands.ICommandService;
import com.tistory.lky1001.sns.application.configuration.queries.IQueryService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.stream.Stream;

@Configuration
public class SnsStartUpConfig {

    @Bean("snsCommandPipeline")
    public Pipeline snsCommandPipeline(ObjectProvider<Command.Handler> commandHandlers, @Qualifier("loggingCommandServiceMiddleware") Command.Middleware loggingCommandServiceMiddleware,
            @Qualifier("transactionalCommandServiceMiddleware") Command.Middleware transactionalCommandServiceMiddleware) {
        return new Pipelinr()
                .with(() -> commandHandlers.stream().filter(item -> item instanceof ICommandService))
                .with(() -> Stream.of(loggingCommandServiceMiddleware, transactionalCommandServiceMiddleware));
    }

    @Bean("snsQueryPipeline")
    public Pipeline snsQueryPipeline(ObjectProvider<Command.Handler> commandHandlers, @Qualifier("transactionalQueryServiceMiddleware") Command.Middleware  transactionalQueryServiceMiddleware) {
        return new Pipelinr()
                .with(() -> commandHandlers.stream().filter(item -> item instanceof IQueryService))
                .with(() -> Stream.of(transactionalQueryServiceMiddleware));
    }

    @Bean("snsNotificationPipeline")
    public Pipeline snsNotificationPipeline(ObjectProvider<Notification.Handler> notificationHandlers) {
        return new Pipelinr()
                .with(notificationHandlers::stream);
    }

    @Bean(name = "snsOutboxJobTaskExecutor")
    public Executor snsOutboxJobTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(20);
        taskExecutor.setThreadNamePrefix("SnsOutboxJobTaskExecutor-");
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean(name = "snsInboxJobTaskExecutor")
    public Executor snsInboxJobTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(20);
        taskExecutor.setThreadNamePrefix("SnsInboxJobTaskExecutor-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
