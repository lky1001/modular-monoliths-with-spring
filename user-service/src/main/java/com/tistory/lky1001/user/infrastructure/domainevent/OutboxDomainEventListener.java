package com.tistory.lky1001.user.infrastructure.domainevent;

import an.awesome.pipelinr.Pipeline;
import com.tistory.lky1001.user.domain.AbstractDomainEvent;
import com.tistory.lky1001.user.infrastructure.outbox.ProcessOutboxCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component("userOutboxDomainEventListener")
public class OutboxDomainEventListener {

    private Pipeline userCommandPipeline;

    public OutboxDomainEventListener(@Qualifier("userCommandPipeline") Pipeline userCommandPipeline) {
        this.userCommandPipeline = userCommandPipeline;
    }

    @Async("userOutboxJobTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleEvent(AbstractDomainEvent event) {
        logger.debug("handleEvent {} thread name - {}", event.getClass().getSimpleName(), Thread.currentThread().getName());
        new ProcessOutboxCommand().execute(userCommandPipeline);
    }
}
