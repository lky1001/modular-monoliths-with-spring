package com.tistory.lky1001.user.application.users;

import com.tistory.lky1001.buildingblocks.application.IExecutionContextAccessor;
import com.tistory.lky1001.user.domain.users.IUserContext;
import com.tistory.lky1001.user.domain.users.UserId;

public class UserContext implements IUserContext {

    private IExecutionContextAccessor executionContextAccessor;

    public UserContext(IExecutionContextAccessor executionContextAccessor) {
        this.executionContextAccessor = executionContextAccessor;
    }

    @Override
    public UserId getUserId() {
        return new UserId(executionContextAccessor.getUserId());
    }
}
