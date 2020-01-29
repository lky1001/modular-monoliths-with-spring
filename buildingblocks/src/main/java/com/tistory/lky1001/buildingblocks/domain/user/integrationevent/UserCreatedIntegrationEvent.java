package com.tistory.lky1001.buildingblocks.domain.user.integrationevent;

import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.AbstractIntegrationEvent;

import java.util.Date;
import java.util.UUID;

public class UserCreatedIntegrationEvent extends AbstractIntegrationEvent {

    private long userId;
    private String name;
    private Date created;

    public UserCreatedIntegrationEvent(String id, Date occurredOn, long userId, String name, Date created) {
        super(id, occurredOn);
        this.userId = userId;
        this.name = name;
        this.created = created;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Date getCreated() {
        return created;
    }
}
