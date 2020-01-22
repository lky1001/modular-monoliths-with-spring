package com.tistory.lky1001.user.infrastructure.outbox;

public interface IOutboxRepository {

    void addMessage(OutboxMessage message);
}
