package com.tistory.lky1001.user.application.contracts;

import com.tistory.lky1001.buildingblocks.domain.DomainValidationException;
import com.tistory.lky1001.buildingblocks.domain.IValidator;
import org.springframework.data.domain.AbstractAggregateRoot;

public class AggregateRoot<A extends AggregateRoot<A>> extends AbstractAggregateRoot {

    protected void validationCheck(IValidator validator) {
        if (!validator.isValid()) {
            throw new DomainValidationException(validator.getMessage());
        }
    }
}
