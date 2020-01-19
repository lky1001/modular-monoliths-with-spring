package com.tistory.lky1001.user.application.contracts;

public interface IUserModule<T extends IResult>  {

    T executeCommand(ICommand<T> command);

    T executeQuery(IQuery<T> query);
}
