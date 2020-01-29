package com.tistory.lky1001.user.infrastructure.outbox;

import an.awesome.pipelinr.Pipeline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component("userProcessOutboxJob")
public class ProcessOutboxJob {

    private Pipeline userCommandPipeline;

    public ProcessOutboxJob(@Qualifier("userCommandPipeline") Pipeline userCommandPipeline) {
        this.userCommandPipeline = userCommandPipeline;
    }

    @Async("userOutboxJobTaskExecutor")
    @Scheduled(fixedDelay = 5000)
    public void processOutboxJob() {
        logger.debug("processOutboxJob thread name - {}", Thread.currentThread().getName());
        new ProcessOutboxCommand().execute(userCommandPipeline);
    }
}
