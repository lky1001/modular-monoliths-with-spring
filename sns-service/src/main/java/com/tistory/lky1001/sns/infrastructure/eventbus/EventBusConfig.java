package com.tistory.lky1001.sns.infrastructure.eventbus;

import com.tistory.lky1001.buildingblocks.domain.user.integrationevent.UserCreatedIntegrationEvent;
import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.IEventsBus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class EventBusConfig {

    public EventBusConfig(IEventsBus eventsBus, @Qualifier("snsIntegrationEventService") IntegrationEventService integrationEventService) {
        integrationEventService.setIntegrationEvents(Arrays.asList(
                UserCreatedIntegrationEvent.class,
                UserCreatedIntegrationEvent.class,
                UserCreatedIntegrationEvent.class
        ));
        eventsBus.subscribe(integrationEventService);
    }
}
