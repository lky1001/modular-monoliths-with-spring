package com.tistory.lky1001.sns.infrastructure;

import com.tistory.lky1001.sns.application.contracts.ICommand;
import com.tistory.lky1001.sns.application.contracts.IQuery;
import com.tistory.lky1001.sns.application.contracts.IResult;
import com.tistory.lky1001.sns.application.contracts.ISnsModule;
import org.springframework.stereotype.Component;

@Component
public class SnsModule implements ISnsModule {


    @Override
    public <R extends IResult> R executeCommand(ICommand<R> command) {
        return null;
    }

    @Override
    public <R extends IResult> R executeQuery(IQuery<R> query) {
        return null;
    }
}
