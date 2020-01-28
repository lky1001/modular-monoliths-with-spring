package com.tistory.lky1001.buildingblocks.infrastructure.eventbus;

public interface IEventsBus {

    <T extends AbstractIntegrationEvent> void publish(T event);
}
