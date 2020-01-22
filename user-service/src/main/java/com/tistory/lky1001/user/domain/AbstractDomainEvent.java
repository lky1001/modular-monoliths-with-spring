package com.tistory.lky1001.user.domain;

import com.tistory.lky1001.buildingblocks.domain.IDomainEvent;

import java.util.Date;
import java.util.UUID;

public abstract class AbstractDomainEvent implements IDomainEvent {

    private String id;
    private Date occurredOn;

    public AbstractDomainEvent() {
        this.id = UUID.randomUUID().toString();
        this.occurredOn = new Date();
    }

    public String getId() {
        return id;
    }

    @Override
    public Date occurredOn() {
        return this.occurredOn;
    }
}
