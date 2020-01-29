package com.tistory.lky1001.buildingblocks.infrastructure.eventbus;

public interface IIntegrationEventService<T extends AbstractIntegrationEvent> {

    void handle(T event);
}
