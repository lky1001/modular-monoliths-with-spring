package com.tistory.lky1001.buildingblocks.infrastructure.inbox;

import java.util.List;

public interface IInbox<T extends AbstractInboxMessage> {

    void add(T message);

    List<T> getAllMessageToProcess();

    void processedMessage(T message);
}
