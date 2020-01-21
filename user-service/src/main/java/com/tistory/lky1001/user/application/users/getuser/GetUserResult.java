package com.tistory.lky1001.user.application.users.getuser;

import com.tistory.lky1001.user.application.contracts.IResult;
import com.tistory.lky1001.user.application.users.UserDto;

public class GetUserResult implements IResult {

    private UserDto userDto;

    public GetUserResult(UserDto userDto) {
        this.userDto = userDto;
    }

    public UserDto getUser() {
        return userDto;
    }
}
