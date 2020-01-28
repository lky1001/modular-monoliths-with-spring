package com.tistory.lky1001.user.application.configuration.queries;

import an.awesome.pipelinr.Command;
import com.tistory.lky1001.user.application.contracts.ICommand;
import com.tistory.lky1001.user.application.contracts.IQuery;
import com.tistory.lky1001.user.application.contracts.IResult;

public interface IQueryService<T extends IQuery<R>, R extends IResult> extends Command.Handler<T, R> {

}
