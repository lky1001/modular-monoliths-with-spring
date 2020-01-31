package com.tistory.lky1001.sns.infrastructure.inbox;

public interface CustomInboxRepository {

    void addMessage(InboxMessage inboxMessage);
}
