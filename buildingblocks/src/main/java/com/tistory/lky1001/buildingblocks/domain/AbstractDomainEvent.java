package com.tistory.lky1001.buildingblocks.domain;

import java.util.Date;

public abstract class AbstractDomainEvent implements IDomainEvent {

    private Date occurredOn = null;

    public AbstractDomainEvent() {
        this.occurredOn = new Date();
    }

    @Override
    public Date occurredOn() {
        return this.occurredOn;
    }
}
