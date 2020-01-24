package com.tistory.lky1001.sns.infrastructure.outbox;

import java.util.List;

public interface IOutboxRepository {

    void addMessage(OutboxMessage message);

    List<OutboxMessage> getAllMessage();
}
