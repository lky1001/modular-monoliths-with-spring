package com.tistory.lky1001.application.api.modules.users;

import com.tistory.lky1001.user.application.authorization.getuserpermissions.GetUserPermissionsQuery;
import com.tistory.lky1001.user.application.authorization.getuserpermissions.UserPermissionsDto;
import com.tistory.lky1001.user.application.contracts.IUserModule;
import com.tistory.lky1001.user.application.users.createuser.CreateUserCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private IUserModule userModule;

    public UserController(IUserModule userModule) {
        this.userModule = userModule;
    }

    @PostMapping
    public void createUser(@RequestBody CreateUserRequest createUserRequest) {
        userModule.executeCommand(new CreateUserCommand(createUserRequest.getEmail(),
                createUserRequest.getPassword(),
                createUserRequest.getName()));
    }

    public void getUserPermissions() {
        UserPermissionsDto result = (UserPermissionsDto) userModule.executeQuery(new GetUserPermissionsQuery());
    }
}
