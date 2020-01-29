package com.tistory.lky1001.sns.application.contracts;

public interface ISnsModule {

    <R extends IResult> R executeCommand(ICommand<R> command);

    <R extends IResult> R executeQuery(IQuery<R> query);
}
