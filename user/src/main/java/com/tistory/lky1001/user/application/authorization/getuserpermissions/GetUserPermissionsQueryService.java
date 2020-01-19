package com.tistory.lky1001.user.application.authorization.getuserpermissions;

import com.tistory.lky1001.user.application.configuration.queries.IQueryService;
import org.springframework.stereotype.Component;

@Component
public class GetUserPermissionsQueryService implements IQueryService<GetUserPermissionsQuery, UserPermissionsDto> {

    @Override
    public UserPermissionsDto handle(GetUserPermissionsQuery query) {
        return null;
    }
}
