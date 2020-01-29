package com.tistory.lky1001.buildingblocks.domain.user.integrationevent;

import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.AbstractIntegrationEvent;

import java.util.Date;

public class UserDeletedIntegrationEvent extends AbstractIntegrationEvent {

    private long userId;
    private String name;
    private Date updated;

    public UserDeletedIntegrationEvent(String id, Date occurredOn, long userId) {
        super(id, occurredOn);
        this.userId = userId;
    }
}
