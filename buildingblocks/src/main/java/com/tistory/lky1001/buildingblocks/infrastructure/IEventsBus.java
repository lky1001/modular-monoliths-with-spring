package com.tistory.lky1001.buildingblocks.infrastructure;

public interface IEventsBus {

    <T extends IntegrationEvent> void publish(T event);
}
