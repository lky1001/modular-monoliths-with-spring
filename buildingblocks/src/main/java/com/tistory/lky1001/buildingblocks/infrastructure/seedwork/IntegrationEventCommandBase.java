package com.tistory.lky1001.buildingblocks.infrastructure.seedwork;

import an.awesome.pipelinr.Voidy;
import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.AbstractIntegrationEvent;

import java.util.UUID;

public class IntegrationEventCommandBase<T extends AbstractIntegrationEvent> implements IIntegrationEventCommand<T, Voidy> {

    private T integrationEvent;
    private String id;

    public IntegrationEventCommandBase(T integrationEvent) {
        this.integrationEvent = integrationEvent;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public T getIntegrationEvent() {
        return integrationEvent;
    }

    @Override
    public String getId() {
        return id;
    }
}
