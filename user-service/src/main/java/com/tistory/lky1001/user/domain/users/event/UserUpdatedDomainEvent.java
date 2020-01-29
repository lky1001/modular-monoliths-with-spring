package com.tistory.lky1001.user.domain.users.event;

import com.tistory.lky1001.user.domain.AbstractDomainEvent;
import com.tistory.lky1001.user.domain.users.User;

public class UserUpdatedDomainEvent extends AbstractDomainEvent {

    private User user;

    public UserUpdatedDomainEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
