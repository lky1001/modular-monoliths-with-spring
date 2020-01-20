package com.tistory.lky1001.user.infrastructure;

import com.tistory.lky1001.user.application.configuration.commands.ICommandService;
import com.tistory.lky1001.user.application.configuration.queries.IQueryService;
import com.tistory.lky1001.user.application.contracts.ICommand;
import com.tistory.lky1001.user.application.contracts.IQuery;
import com.tistory.lky1001.user.application.contracts.IResult;
import com.tistory.lky1001.user.application.contracts.IUserModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserModule implements IUserModule {

    private List<ICommandService> commandServices;
    private List<IQueryService> queryServices;

    @Autowired
    public void setCommandServices(List<ICommandService> commandServices) {
        this.commandServices = commandServices;
    }

    @Autowired
    public void setQueryServices(List<IQueryService> queryServices) {
        this.queryServices = queryServices;
    }

    @Override
    public IResult executeCommand(ICommand command) {
        for (ICommandService commandService : this.commandServices) {
            if (commandService.isType(command)) {
                return commandService.handle(command);
            }
        }

        return null;
    }

    @Override
    public IResult executeQuery(IQuery query) {
        for (IQueryService queryService : this.queryServices) {
            if (queryService.isType(query)) {
                return queryService.handle(query);
            }
        }
        return null;
    }
}
