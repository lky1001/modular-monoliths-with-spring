package com.tistory.lky1001.sns.infrastructure.eventbus;

import com.tistory.lky1001.buildingblocks.domain.user.integrationevent.UserCreatedIntegrationEvent;
import com.tistory.lky1001.buildingblocks.domain.user.integrationevent.UserDeletedIntegrationEvent;
import com.tistory.lky1001.buildingblocks.domain.user.integrationevent.UserUpdatedIntegrationEvent;
import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.IEventsBus;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class EventBusConfig {

    public EventBusConfig(IEventsBus eventsBus) {

        eventsBus.subscribe(new IntegrationEventService(Arrays.asList(
                UserCreatedIntegrationEvent.class,
                UserUpdatedIntegrationEvent.class,
                UserDeletedIntegrationEvent.class
        )));
    }
}
