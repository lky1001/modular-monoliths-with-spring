package com.tistory.lky1001.user.application.users.createuser;

import com.tistory.lky1001.buildingblocks.domain.user.integrationevent.UserCreatedIntegrationEvent;
import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.IEventsBus;
import com.tistory.lky1001.buildingblocks.infrastructure.seedwork.DomainEventNotificationBase;
import com.tistory.lky1001.buildingblocks.infrastructure.seedwork.IDomainEventNotificationService;
import com.tistory.lky1001.user.domain.users.User;
import com.tistory.lky1001.user.domain.users.event.UserCreatedDomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserCreatedNotificationService implements IDomainEventNotificationService<DomainEventNotificationBase<UserCreatedDomainEvent>> {

    private IEventsBus eventsBus;

    public UserCreatedNotificationService(IEventsBus eventsBus) {
        this.eventsBus = eventsBus;
    }

    @Override
    public void handle(DomainEventNotificationBase<UserCreatedDomainEvent> notification) {
        logger.debug("{} - DomainEventNotificationBase", Thread.currentThread().getName());
        UserCreatedDomainEvent domainEvent = notification.getDomainEvent();
        User user = domainEvent.getUser();

        this.eventsBus.publish(new UserCreatedIntegrationEvent(
                domainEvent.getId(),
                domainEvent.occurredOn(),
                user.getId(),
                user.getName(),
                user.getCreated()
        ));
    }
}
