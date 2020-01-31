package com.tistory.lky1001.user.infrastructure;

import an.awesome.pipelinr.Pipeline;
import com.tistory.lky1001.user.application.contracts.ICommand;
import com.tistory.lky1001.user.application.contracts.IQuery;
import com.tistory.lky1001.user.application.contracts.IResult;
import com.tistory.lky1001.user.application.contracts.IUserModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UserModule implements IUserModule {

    private Pipeline userCommandPipeline;
    private Pipeline userQueryPipeline;

    public UserModule(@Qualifier("userCommandPipeline") Pipeline userCommandPipeline, @Qualifier("userQueryPipeline") Pipeline userQueryPipeline) {
        Assert.notNull(userCommandPipeline, "UserCommandPipeline is required.");
        Assert.notNull(userQueryPipeline, "UserQueryPipeline is required.");
        this.userCommandPipeline = userCommandPipeline;
        this.userQueryPipeline = userQueryPipeline;
    }

    @Override
    public <R extends IResult> R executeCommand(ICommand<R> command) {
        return command.execute(userCommandPipeline);
    }

    @Override
    public <R extends IResult> R executeQuery(IQuery<R> query) {
        return query.execute(userQueryPipeline);
    }
}
