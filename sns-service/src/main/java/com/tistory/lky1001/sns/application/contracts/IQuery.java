package com.tistory.lky1001.sns.application.contracts;

import an.awesome.pipelinr.Command;

import java.util.UUID;

public interface IQuery<R extends IResult> extends Command<R> {

    UUID getId();
}
