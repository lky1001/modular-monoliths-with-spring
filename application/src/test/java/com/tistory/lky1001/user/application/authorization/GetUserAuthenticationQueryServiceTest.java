package com.tistory.lky1001.user.application.authorization;

import com.tistory.lky1001.Application;
import com.tistory.lky1001.user.application.users.createuser.CreateUserCommand;
import com.tistory.lky1001.user.application.users.createuser.CreateUserCommandService;
import com.tistory.lky1001.user.domain.DomainRegistry;
import com.tistory.lky1001.user.domain.users.IRoleRepository;
import com.tistory.lky1001.user.domain.users.IUserRepository;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class GetUserAuthenticationQueryServiceTest {

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

    private CreateUserCommandService createUserCommandService;

    private GetUserAuthenticationQueryService getUserAuthenticationQueryService;

    @Before
    public void setUp() throws SQLException {
        createUserCommandService = new CreateUserCommandService(userRepository, roleRepository);
        getUserAuthenticationQueryService = new GetUserAuthenticationQueryService(userRepository, roleRepository);

        if (setUpIsDone) {
            return;
        }

        Resource resource = context.getResource("classpath:users.sql");
        EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("utf-8"));
        ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);

        setUpIsDone = true;
    }

    @Test
    public void successCreateUserAndGetUserAuthenticationTest() {
        CreateUserCommand createUserCommand = new CreateUserCommand("test1@gmail.com", "test1234", "test");
        createUserCommandService.handle(createUserCommand);

        var getUserAuthenticationResult = getUserAuthenticationQueryService.handle(new GetUserAuthenticationQuery("test1@gmail.com"));

        IPasswordManager passwordManager = DomainRegistry.passwordManager();

        assertTrue(getUserAuthenticationResult.isAuthenticated());
        assertEquals("test1@gmail.com", getUserAuthenticationResult.getUserAuthentication().getEmail());
        assertTrue(passwordManager.matches("test1234", getUserAuthenticationResult.getUserAuthentication().getPassword()));
        assertEquals(1, getUserAuthenticationResult.getUserAuthentication().getRoles().size());
        assertEquals("ROLE_USER", getUserAuthenticationResult.getUserAuthentication().getRoles().get(0).getName());
    }

    @Test
    public void successGetUserAuthenticationFailureTest() {
        CreateUserCommand createUserCommand = new CreateUserCommand("test2@gmail.com", "test1234", "test");
        createUserCommandService.handle(createUserCommand);

        var getUserAuthenticationResult = getUserAuthenticationQueryService.handle(new GetUserAuthenticationQuery("test123@gmail.com"));

        assertFalse(getUserAuthenticationResult.isAuthenticated());
    }
}
