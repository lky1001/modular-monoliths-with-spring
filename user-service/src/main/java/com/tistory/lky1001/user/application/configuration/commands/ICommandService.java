package com.tistory.lky1001.user.application.configuration.commands;

import an.awesome.pipelinr.Command;
import com.tistory.lky1001.user.application.contracts.ICommand;
import com.tistory.lky1001.user.application.contracts.IResult;

public interface ICommandService<T extends ICommand<R>, R extends IResult> extends Command.Handler<T, R> {

}
