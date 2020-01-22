package com.tistory.lky1001.user.domain.users.event;

import com.tistory.lky1001.user.domain.AbstractDomainEvent;
import com.tistory.lky1001.user.domain.users.User;

public class UserCreatedDomainEvent extends AbstractDomainEvent {

    private User user;

    public UserCreatedDomainEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
