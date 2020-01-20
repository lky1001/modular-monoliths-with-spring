package com.tistory.lky1001.user.application.users.createuser;

import com.tistory.lky1001.user.application.contracts.IResult;

public class CreateUserResult implements IResult {

    private boolean result;

    public CreateUserResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return this.result;
    }
}
