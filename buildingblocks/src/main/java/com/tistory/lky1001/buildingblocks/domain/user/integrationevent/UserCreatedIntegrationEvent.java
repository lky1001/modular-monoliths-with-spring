package com.tistory.lky1001.buildingblocks.domain.user.integrationevent;

import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.AbstractIntegrationEvent;

import java.util.Date;
import java.util.UUID;

public class UserCreatedIntegrationEvent extends AbstractIntegrationEvent {

    private long userId;
    private String name;
    private Date created;
    private Date updated;

    public UserCreatedIntegrationEvent(UUID id, Date occurredOn, long userId, String name, Date created, Date updated) {
        super(id, occurredOn);
        this.userId = userId;
        this.name = name;
        this.created = created;
        this.updated = updated;
    }
}
