package com.tistory.lky1001.sns.application.user;

import an.awesome.pipelinr.Voidy;
import com.tistory.lky1001.buildingblocks.domain.user.integrationevent.UserCreatedIntegrationEvent;
import com.tistory.lky1001.buildingblocks.infrastructure.seedwork.IIntegrationEventCommandService;
import com.tistory.lky1001.buildingblocks.infrastructure.seedwork.IntegrationEventCommandBase;
import com.tistory.lky1001.sns.domain.users.IUserRepository;
import com.tistory.lky1001.sns.domain.users.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service("snsUserCreatedIntegrationEventService")
public class UserCreatedIntegrationEventService implements IIntegrationEventCommandService<IntegrationEventCommandBase<UserCreatedIntegrationEvent>, Voidy> {

    private IUserRepository userRepository;

    public UserCreatedIntegrationEventService(@Qualifier("snsUserRepository") IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Voidy handle(IntegrationEventCommandBase<UserCreatedIntegrationEvent> command) {
        logger.debug("{} - Start UserCreatedIntegrationEventService handle", Thread.currentThread().getName());

        var event = command.getIntegrationEvent();

        User user = User.createUser(event.getUserId(), event.getName(), event.getCreated());

        userRepository.addUser(user);

        return new Voidy();
    }
}
