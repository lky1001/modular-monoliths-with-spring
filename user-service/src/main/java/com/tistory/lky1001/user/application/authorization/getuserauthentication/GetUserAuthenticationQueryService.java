package com.tistory.lky1001.user.application.authorization.getuserauthentication;

import com.tistory.lky1001.user.application.configuration.queries.IQueryService;
import com.tistory.lky1001.user.domain.users.IRoleRepository;
import com.tistory.lky1001.user.domain.users.IUserRepository;
import com.tistory.lky1001.user.domain.users.Role;
import com.tistory.lky1001.user.domain.users.User;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetUserAuthenticationQueryService implements IQueryService<GetUserAuthenticationQuery, GetUserAuthenticationResult> {

    private IUserRepository userRepository;
    private IRoleRepository roleRepository;

    public GetUserAuthenticationQueryService(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public GetUserAuthenticationResult handle(GetUserAuthenticationQuery query) {
        val user = userRepository.getByEmail(User.getEncodedEmail(query.getEmail()));

        if (user == null) {
            return new GetUserAuthenticationResult("User not found.");
        }

        List<Role> roles = roleRepository.getByRoleIds(user.getRoleIds().stream().collect(Collectors.toList()));

        List<RoleDto> roleDtos = roles.stream().map(role -> new RoleDto(role.getName())).collect(Collectors.toList());

        return new GetUserAuthenticationResult(new UserAuthenticationDto(
                user.getId(),
                user.getEmail(),
                user.getEncryptedPassword(),
                roleDtos
        ));
    }
}
