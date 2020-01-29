package com.tistory.lky1001.buildingblocks.infrastructure.seedwork;

import com.tistory.lky1001.buildingblocks.domain.IDomainEvent;

import java.util.UUID;

public class DomainEventNotificationBase<T extends IDomainEvent> implements IDomainEventNotification<T> {

    private T domainEvent;
    private String id;

    public DomainEventNotificationBase(T domainEvent) {
        this.domainEvent = domainEvent;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public T getDomainEvent() {
        return domainEvent;
    }

    @Override
    public String getId() {
        return id;
    }
}
