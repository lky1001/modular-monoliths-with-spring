package com.tistory.lky1001.buildingblocks.infrastructure.eventbus;

import com.google.common.eventbus.EventBus;

public class GuavaEventBus implements IEventsBus {

    private EventBus eventBus = new EventBus();

    @Override
    public <T extends AbstractIntegrationEvent> void publish(T event) {
        eventBus.post(event);
    }

    @Override
    public <T extends AbstractIntegrationEvent> void subscribe(IIntegrationEventService<T> service) {
        eventBus.register(service);
    }
}
