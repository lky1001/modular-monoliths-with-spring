package com.tistory.lky1001.sns.infrastructure.inbox;

import an.awesome.pipelinr.Pipeline;
import com.tistory.lky1001.sns.domain.AbstractDomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.Assert;

@Slf4j
@Component("snsInboxDomainEventListener")
public class InboxIntegrationEventListener {

    private Pipeline snsCommandPipeline;

    public InboxIntegrationEventListener(@Qualifier("snsCommandPipeline") Pipeline snsCommandPipeline) {
        Assert.notNull(snsCommandPipeline, "SnsCommandPipeline is required.");
        this.snsCommandPipeline = snsCommandPipeline;
    }

    @Async("snsInboxJobTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleEvent(AbstractDomainEvent event) {
        logger.debug("{} - processOutboxJob", Thread.currentThread().getName());
        new ProcessInboxCommand().execute(snsCommandPipeline);
    }
}
