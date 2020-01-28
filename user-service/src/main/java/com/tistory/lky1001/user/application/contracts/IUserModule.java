package com.tistory.lky1001.user.application.contracts;

public interface IUserModule {

    <R extends IResult> R executeCommand(ICommand<R> command);

    <R extends IResult> R executeQuery(IQuery<R> query);
}
