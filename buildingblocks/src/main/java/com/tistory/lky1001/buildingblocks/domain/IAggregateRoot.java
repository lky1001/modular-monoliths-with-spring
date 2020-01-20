package com.tistory.lky1001.buildingblocks.domain;

public interface IAggregateRoot {

    void addDomainEvents(IDomainEvent event);
}
