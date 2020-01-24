package com.tistory.lky1001.sns.infrastructure.outbox;

import com.tistory.lky1001.buildingblocks.infrastructure.outbox.IOutbox;
import org.springframework.stereotype.Component;

@Component("snsOutbox")
public class Outbox implements IOutbox<OutboxMessage> {

    private IOutboxRepository outboxRepository;

    public Outbox(IOutboxRepository outboxRepository) {
        this.outboxRepository = outboxRepository;
    }

    @Override
    public void add(OutboxMessage message) {
        outboxRepository.addMessage(message);
    }
}
