package com.tistory.lky1001.user.application.users;

import com.tistory.lky1001.Application;
import com.tistory.lky1001.user.application.users.createuser.CreateUserCommand;
import com.tistory.lky1001.user.application.users.createuser.CreateUserCommandService;
import com.tistory.lky1001.user.application.users.createuser.CreateUserResult;
import com.tistory.lky1001.user.domain.users.IRoleRepository;
import com.tistory.lky1001.user.domain.users.IUserRepository;
import com.tistory.lky1001.user.domain.users.Role;
import com.tistory.lky1001.user.domain.users.User;
import com.tistory.lky1001.user.domain.users.event.UserCreatedDomainEvent;
import com.tistory.lky1001.user.infrastructure.outbox.IOutboxRepository;
import com.tistory.lky1001.user.infrastructure.outbox.OutboxMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class CreateUserCommandServiceTest {

    private static boolean setUpIsDone = false;

    @Autowired
    @Qualifier("userDataSource")
    private DataSource dataSource;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IOutboxRepository outboxRepository;

    private CreateUserCommandService createUserCommandService;

    @Before
    public void setUp() throws SQLException {
        createUserCommandService = new CreateUserCommandService(userRepository, roleRepository);

        if (setUpIsDone) {
            return;
        }

        Resource resource = context.getResource("classpath:users.sql");
        EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("utf-8"));
        ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);

        setUpIsDone = true;
    }

    @Test
    public void successCreateUserRepoTest() {
        User user = User.createUser("test@gmail.com", "test1234", "test");

        Role role = roleRepository.findById(1).orElseThrow();

        user.addRole(role);

        userRepository.save(user);

        User savedUser = userRepository.findById(user.getId()).orElseThrow();

        Role savedUserRole = roleRepository.findById(savedUser.getRoleIds().iterator().next()).orElseThrow();

        long userCount = userRepository.count();

        assertEquals(1, role.getId());
        assertEquals("ROLE_ADMIN", role.getName());
        assertTrue(user.getId() > 0);
        assertEquals("ROLE_ADMIN", savedUserRole.getName());
        assertEquals("ROLE_ADMIN", savedUserRole.getName());
        assertEquals("normal admin", savedUserRole.getDesc());
        assertTrue(userCount > 0);
    }

    @Test
    public void successCreateUserWithRoleTest() {
        CreateUserCommand createUserCommand = new CreateUserCommand("aaa@gmail.com", "12345678", "name");

        CreateUserResult createUserResult = createUserCommandService.handle(createUserCommand);

        assertTrue(createUserResult.getResult());
    }

    @Test
    public void successUserCreatedDomainEvent() {
        CreateUserCommand createUserCommand = new CreateUserCommand("aaa@gmail.com", "12345678", "name");

        createUserCommandService.handle(createUserCommand);

        List<OutboxMessage> outboxMessages = outboxRepository.getAllMessage();

        assertEquals(1, outboxMessages.size());
        assertEquals(UserCreatedDomainEvent.class.getName(), outboxMessages.get(0).getType());
    }
}
