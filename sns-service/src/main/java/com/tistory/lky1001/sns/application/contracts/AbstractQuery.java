package com.tistory.lky1001.sns.application.contracts;

import java.util.UUID;

public class AbstractQuery {

    private UUID id;

    public AbstractQuery() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }
}
