package com.tistory.lky1001.buildingblocks.infrastructure.outbox;

import java.util.List;

public interface IOutbox<T extends AbstractOutboxMessage> {

    void add(T message);

    List<T> getAllMessageToProcess();

    void processedMessage(T message);
}
