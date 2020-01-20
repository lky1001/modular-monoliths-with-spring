package com.tistory.lky1001.user.application.users.createuser;

import com.tistory.lky1001.buildingblocks.domain.DomainValidationException;
import com.tistory.lky1001.user.application.configuration.commands.ICommandService;
import com.tistory.lky1001.user.application.contracts.EnumDomainMessage;
import com.tistory.lky1001.user.application.contracts.SecurityRole;
import com.tistory.lky1001.user.domain.users.IRoleRepository;
import com.tistory.lky1001.user.domain.users.IUserRepository;
import com.tistory.lky1001.user.domain.users.Role;
import com.tistory.lky1001.user.domain.users.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserCommandService implements ICommandService<CreateUserCommand, CreateUserResult> {

    private IUserRepository userRepository;
    private IRoleRepository roleRepository;

    public CreateUserCommandService(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateUserResult handle(CreateUserCommand command) {
        User newUser = User.createUser(command.getEmail(), command.getPassword(), command.getName());

        User user = this.userRepository.getByEmail(newUser.getEncryptedEmail());

        if (user != null) {
            throw new DomainValidationException(EnumDomainMessage.EMAIL_ALREADY_EXIST.getCode(), EnumDomainMessage.EMAIL_ALREADY_EXIST.getMsg());
        }

        Role userRole = this.roleRepository.getByRoleName(SecurityRole.ROLE_USER);

        newUser.addRole(userRole);

        userRepository.save(newUser);

        return new CreateUserResult(true);
    }
}
