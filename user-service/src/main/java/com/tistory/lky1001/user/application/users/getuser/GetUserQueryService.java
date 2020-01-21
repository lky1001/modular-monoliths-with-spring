package com.tistory.lky1001.user.application.users.getuser;

import com.tistory.lky1001.buildingblocks.application.IExecutionContextAccessor;
import com.tistory.lky1001.user.application.configuration.queries.IQueryService;
import com.tistory.lky1001.user.application.users.UserDto;
import com.tistory.lky1001.user.domain.users.IUserRepository;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
public class GetUserQueryService implements IQueryService<GetUserQuery, GetUserResult> {

    private IUserRepository userRepository;
    private IExecutionContextAccessor executionContextAccessor;

    public GetUserQueryService(IUserRepository userRepository, IExecutionContextAccessor executionContextAccessor) {
        this.userRepository = userRepository;
        this.executionContextAccessor = executionContextAccessor;
    }

    @Override
    public GetUserResult handle(GetUserQuery query) {
        val userId = executionContextAccessor.getUserId();

        return userRepository.getById(userId)
                .map(user -> new GetUserResult(new UserDto(user.getId(), user.getEmail(), user.getName())))
                .orElse(new GetUserResult(null));
    }
}
