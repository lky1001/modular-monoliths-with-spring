package com.tistory.lky1001.sns.infrastructure.outbox;

public interface SnsOutboxRepository {

    void addMessage(OutboxMessage outboxMessage);
}

