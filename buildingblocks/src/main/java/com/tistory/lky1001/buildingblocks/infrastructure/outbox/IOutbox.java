package com.tistory.lky1001.buildingblocks.infrastructure.outbox;

public interface IOutbox<T extends AbstractOutboxMessage> {

    void add(T message);
}
