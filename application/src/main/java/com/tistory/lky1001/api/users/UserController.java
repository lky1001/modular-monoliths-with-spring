package com.tistory.lky1001.api.users;

import com.tistory.lky1001.user.application.contracts.IUserModule;
import com.tistory.lky1001.user.application.users.createuser.CreateUserCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createUser(@RequestBody CreateUserRequest createUserRequest) {
        userModule.executeCommand(new CreateUserCommand(createUserRequest.getEmail(),
                createUserRequest.getPassword(),
                createUserRequest.getName()));

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
