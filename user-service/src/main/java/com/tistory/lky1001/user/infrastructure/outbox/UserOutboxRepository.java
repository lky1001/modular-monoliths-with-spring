package com.tistory.lky1001.user.infrastructure.outbox;

public interface UserOutboxRepository {

    void addMessage(OutboxMessage outboxMessage);
}
