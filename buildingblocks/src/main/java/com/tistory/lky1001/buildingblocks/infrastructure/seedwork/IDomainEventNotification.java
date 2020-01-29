package com.tistory.lky1001.buildingblocks.infrastructure.seedwork;

import an.awesome.pipelinr.Notification;
import com.tistory.lky1001.buildingblocks.domain.IDomainEvent;

public interface IDomainEventNotification<T extends IDomainEvent> extends Notification {

    T getDomainEvent();

    String getId();
}
