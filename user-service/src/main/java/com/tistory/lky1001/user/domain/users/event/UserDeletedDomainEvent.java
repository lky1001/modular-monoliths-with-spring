package com.tistory.lky1001.user.domain.users.event;

import com.tistory.lky1001.user.domain.AbstractDomainEvent;

public class UserDeletedDomainEvent extends AbstractDomainEvent {

    private int userId;

    public UserDeletedDomainEvent(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
