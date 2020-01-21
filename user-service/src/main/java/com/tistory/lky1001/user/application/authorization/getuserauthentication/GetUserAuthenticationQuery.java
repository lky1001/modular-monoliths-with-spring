package com.tistory.lky1001.user.application.authorization.getuserauthentication;

import com.tistory.lky1001.user.application.contracts.AbstractQuery;
import com.tistory.lky1001.user.application.contracts.IQuery;

public class GetUserAuthenticationQuery extends AbstractQuery implements IQuery<GetUserAuthenticationResult> {

    private String email;

    public GetUserAuthenticationQuery(String email) {
        super();
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
