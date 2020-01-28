package com.tistory.lky1001.buildingblocks.infrastructure.eventbus;

import java.util.Date;
import java.util.UUID;

public abstract class AbstractIntegrationEvent {

    private UUID id;
    private Date occurredOn;

    protected AbstractIntegrationEvent(UUID id, Date occurredOn) {
        this.id = id;
        this.occurredOn = occurredOn;
    }

    public UUID getId() {
        return this.id;
    }

    public Date getOccurredOn() {
        return this.occurredOn;
    }
}
