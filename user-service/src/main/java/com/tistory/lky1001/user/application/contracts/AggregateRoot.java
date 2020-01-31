package com.tistory.lky1001.user.application.contracts;

import com.tistory.lky1001.buildingblocks.domain.DomainValidationException;
import com.tistory.lky1001.buildingblocks.domain.IAggregateRoot;
import com.tistory.lky1001.buildingblocks.domain.IDomainEvent;
import com.tistory.lky1001.buildingblocks.domain.IValidator;
import org.springframework.data.domain.AbstractAggregateRoot;

public abstract class AggregateRoot<A extends AggregateRoot<A>> extends AbstractAggregateRoot<A> implements IAggregateRoot {

    protected void validationCheck(IValidator validator) {
        if (!validator.isValid()) {
            throw new DomainValidationException(validator);
        }
    }

    @Override
    public void addDomainEvents(IDomainEvent domainEvent) {
        super.registerEvent(domainEvent);
    }

    @Override
    protected <T> T registerEvent(T event) {
        throw new RuntimeException();
    }
}
