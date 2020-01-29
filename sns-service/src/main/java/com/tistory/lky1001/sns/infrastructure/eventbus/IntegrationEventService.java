package com.tistory.lky1001.sns.infrastructure.eventbus;

import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.AbstractIntegrationEvent;
import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.GuavaIntegrationEventService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class IntegrationEventService extends GuavaIntegrationEventService<AbstractIntegrationEvent> {

    public IntegrationEventService(List<Class<?>> events) {
        super(events);
    }

    @Override
    protected void handleEvent(AbstractIntegrationEvent event) {
        logger.debug("handleEvent {} thread name - {}", event.getClass().getSimpleName(), Thread.currentThread().getName());
    }
}
