package com.tistory.lky1001.sns.application.configuration.commands;

import an.awesome.pipelinr.Command;
import com.tistory.lky1001.sns.application.contracts.ICommand;
import com.tistory.lky1001.sns.application.contracts.IResult;

public interface ICommandService<T extends ICommand<R>, R extends IResult> extends Command.Handler<T, R> {

}
