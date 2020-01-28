package com.tistory.lky1001.user.application.contracts;

import an.awesome.pipelinr.Command;

import java.util.UUID;

public interface ICommand<R> extends Command<R> {

    UUID getId();
}
