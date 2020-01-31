package com.tistory.lky1001.sns.infrastructure.eventbus;

import an.awesome.pipelinr.Pipeline;
import com.tistory.lky1001.sns.infrastructure.inbox.ProcessInboxCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.Assert;

@Slf4j
@Component("snsInboxEventListener")
public class InboxEventListener {

    private Pipeline snsCommandPipeline;

    public InboxEventListener(@Qualifier("snsCommandPipeline") Pipeline snsCommandPipeline) {
        Assert.notNull(snsCommandPipeline, "SnsCommandPipeline is required.");
        this.snsCommandPipeline = snsCommandPipeline;
    }

    @Async("userOutboxJobTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleEvent(ProcessInboxCommand event) {
        logger.debug("{} - handleEvent {}", Thread.currentThread().getName(), event.getClass().getSimpleName());
        event.execute(snsCommandPipeline);
    }
}
