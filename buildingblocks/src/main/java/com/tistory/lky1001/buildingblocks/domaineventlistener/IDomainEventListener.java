package com.tistory.lky1001.buildingblocks.domaineventlistener;

import com.tistory.lky1001.buildingblocks.domain.IDomainEvent;

public interface IDomainEventListener<T extends IDomainEvent> {

    void handleEvent(T event);
}
