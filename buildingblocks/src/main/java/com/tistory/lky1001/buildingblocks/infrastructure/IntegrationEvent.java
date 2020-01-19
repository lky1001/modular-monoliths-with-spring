package com.tistory.lky1001.buildingblocks.infrastructure;

import java.util.Date;
import java.util.UUID;

public abstract class IntegrationEvent {

    private UUID id;
    private Date occurredOn;

    protected IntegrationEvent(UUID id, Date occurredOn) {
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
