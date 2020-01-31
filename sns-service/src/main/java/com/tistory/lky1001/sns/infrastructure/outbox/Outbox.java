package com.tistory.lky1001.sns.infrastructure.outbox;

import com.tistory.lky1001.buildingblocks.infrastructure.outbox.IOutbox;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("snsOutbox")
public class Outbox implements IOutbox<OutboxMessage> {

    private IOutboxRepository outboxRepository;

    public Outbox(@Qualifier("snsOutboxRepository") IOutboxRepository outboxRepository) {
        this.outboxRepository = outboxRepository;
    }

    @Override
    public void add(OutboxMessage message) {
        outboxRepository.addMessage(message);
    }

    @Override
    public List<OutboxMessage> getAllMessageToProcess() {
        return this.outboxRepository.getAllMessageToProcess();
    }

    @Override
    public void processedMessage(OutboxMessage message) {
        message.processed();
        this.outboxRepository.save(message);
    }
}
