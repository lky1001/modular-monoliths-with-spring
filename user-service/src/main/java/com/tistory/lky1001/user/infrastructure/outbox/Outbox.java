package com.tistory.lky1001.user.infrastructure.outbox;

import com.tistory.lky1001.buildingblocks.infrastructure.outbox.IOutbox;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component("userOutbox")
public class Outbox implements IOutbox<OutboxMessage> {

    private IOutboxRepository outboxRepository;

    public Outbox(@Qualifier("userOutboxRepository") IOutboxRepository outboxRepository) {
        Assert.notNull(outboxRepository, "UserOutboxRepository is required.");
        this.outboxRepository = outboxRepository;
    }

    @Override
    public void add(OutboxMessage message) {
        this.outboxRepository.addMessage(message);
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
