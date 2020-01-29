package com.tistory.lky1001.buildingblocks.infrastructure.inbox;

public interface IInbox<T extends AbstractInboxMessage> {

    void add(T message);
}
