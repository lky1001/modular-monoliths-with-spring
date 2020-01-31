package com.tistory.lky1001.buildingblocks.infrastructure.eventbus;

import com.google.common.eventbus.Subscribe;

import java.util.List;

public abstract class GuavaIntegrationEventService<T extends AbstractIntegrationEvent> implements IIntegrationEventService<T> {

    protected List<Class<? extends AbstractIntegrationEvent>> events;

    public void setIntegrationEvents(List<Class<? extends AbstractIntegrationEvent>> events) {
        this.events = events;
    }

    @Subscribe
    @Override
    public void handle(T event) {
        if (events.stream().anyMatch(e -> e.isAssignableFrom(event.getClass()))) {
            handleEvent(event);
        }
    }

    protected abstract void handleEvent(T event);
}
