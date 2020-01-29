package com.tistory.lky1001.buildingblocks.infrastructure.eventbus;

import com.google.common.eventbus.Subscribe;

import java.util.List;

public abstract class GuavaIntegrationEventService<T extends AbstractIntegrationEvent> implements IIntegrationEventService<T> {

    private List<Class<?>> events;

    protected GuavaIntegrationEventService(List<Class<?>> events) {
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
