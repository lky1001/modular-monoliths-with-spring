package com.tistory.lky1001.user.domain.users.event;

import com.tistory.lky1001.user.domain.AbstractDomainEvent;
import com.tistory.lky1001.user.domain.users.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserCreatedDomainEvent extends AbstractDomainEvent {

    private User user;

    public UserCreatedDomainEvent(String id, User user) {
        super(id);
        this.user = user;
    }

    public UserCreatedDomainEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
