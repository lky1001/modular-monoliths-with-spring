package com.tistory.lky1001.user.infrastructure.outbox;

import an.awesome.pipelinr.Pipeline;
import com.tistory.lky1001.user.domain.AbstractDomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.Assert;

@Slf4j
@Component("userOutboxDomainEventListener")
public class OutboxDomainEventListener {

    private Pipeline userCommandPipeline;

    public OutboxDomainEventListener(@Qualifier("userCommandPipeline") Pipeline userCommandPipeline) {
        Assert.notNull(userCommandPipeline, "UserCommandPipeline is required.");
        this.userCommandPipeline = userCommandPipeline;
    }

    @Async("userOutboxJobTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleEvent(AbstractDomainEvent event) {
        logger.debug("{} - handleEvent {}", Thread.currentThread().getName(), event.getClass().getSimpleName());
        new ProcessOutboxCommand().execute(userCommandPipeline);
    }
}
