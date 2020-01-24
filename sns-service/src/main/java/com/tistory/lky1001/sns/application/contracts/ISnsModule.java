package com.tistory.lky1001.sns.application.contracts;

public interface ISnsModule<T extends IResult> {

    T executeCommand(ICommand<T> command);

    T executeQuery(IQuery<T> query);
}
