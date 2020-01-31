package com.tistory.lky1001.user.infrastructure.outbox;

import an.awesome.pipelinr.Pipeline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component("userProcessOutboxJob")
public class ProcessOutboxJob {

    private Pipeline userCommandPipeline;

    public ProcessOutboxJob(@Qualifier("userCommandPipeline") Pipeline userCommandPipeline) {
        Assert.notNull(userCommandPipeline, "UserCommandPipeline is required.");
        this.userCommandPipeline = userCommandPipeline;
    }

    @Async("userOutboxJobTaskExecutor")
    @Scheduled(fixedDelay = 5000)
    public void processOutboxJob() {
        logger.debug("{} - start processOutboxJob", Thread.currentThread().getName());
        new ProcessOutboxCommand().execute(userCommandPipeline);
        logger.debug("{} - end processOutboxJob", Thread.currentThread().getName());
    }
}
