package com.tistory.lky1001.sns.infrastructure.inbox;

import java.util.List;

public interface IInboxRepository {

    void addMessage(InboxMessage message);

    List<InboxMessage> getAllMessage();
}
