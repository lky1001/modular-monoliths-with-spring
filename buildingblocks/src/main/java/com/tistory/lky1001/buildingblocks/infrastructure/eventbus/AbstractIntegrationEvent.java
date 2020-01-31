package com.tistory.lky1001.buildingblocks.infrastructure.eventbus;

import java.util.Date;

public abstract class AbstractIntegrationEvent {

    private String id;
    private Date occurredOn;

    protected AbstractIntegrationEvent() {
    }

    protected AbstractIntegrationEvent(String id, Date occurredOn) {
        this.id = id;
        this.occurredOn = occurredOn;
    }

    public String getId() {
        return this.id;
    }

    public Date getOccurredOn() {
        return this.occurredOn;
    }
}
