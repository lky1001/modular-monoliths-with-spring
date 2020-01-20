package com.tistory.lky1001.user.application.contracts;

import java.util.UUID;

public class AbstractCommand implements ICommand {

    private UUID id;

    public AbstractCommand() {
        this.id = UUID.randomUUID();
    }

    public AbstractCommand(UUID id) {
        this.id = id;
    }

    @Override
    public UUID getId() {
        return this.id;
    }
}
