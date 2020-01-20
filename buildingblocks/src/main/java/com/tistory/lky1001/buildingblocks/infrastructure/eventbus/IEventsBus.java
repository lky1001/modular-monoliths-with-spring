package com.tistory.lky1001.buildingblocks.infrastructure.eventbus;

public interface IEventsBus {

    <T extends IntegrationEvent> void publish(T event);
}
