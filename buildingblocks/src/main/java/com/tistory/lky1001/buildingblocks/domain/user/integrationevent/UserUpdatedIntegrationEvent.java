package com.tistory.lky1001.buildingblocks.domain.user.integrationevent;

import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.AbstractIntegrationEvent;

import java.util.Date;
import java.util.UUID;

public class UserUpdatedIntegrationEvent extends AbstractIntegrationEvent {

    private long userId;
    private String name;
    private Date updated;

    public UserUpdatedIntegrationEvent(String id, Date occurredOn, long userId, String name, Date updated) {
        super(id, occurredOn);
        this.userId = userId;
        this.name = name;
        this.updated = updated;
    }
}
