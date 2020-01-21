package com.tistory.lky1001.user.application.users;

import com.tistory.lky1001.Application;
import com.tistory.lky1001.buildingblocks.application.IExecutionContextAccessor;
import com.tistory.lky1001.user.application.users.createuser.CreateUserCommand;
import com.tistory.lky1001.user.application.users.createuser.CreateUserCommandService;
import com.tistory.lky1001.user.application.users.getuser.GetUserQuery;
import com.tistory.lky1001.user.application.users.getuser.GetUserQueryService;
import com.tistory.lky1001.user.domain.users.IRoleRepository;
import com.tistory.lky1001.user.domain.users.IUserRepository;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class GetUserServiceTest {

    private static boolean setUpIsDone = false;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Mock
    private IExecutionContextAccessor executionContextAccessor;

    private CreateUserCommandService createUserCommandService;

    private GetUserQueryService getUserQueryService;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        createUserCommandService = new CreateUserCommandService(userRepository, roleRepository);
        getUserQueryService = new GetUserQueryService(userRepository, executionContextAccessor);

        if (setUpIsDone) {
            return;
        }

        Resource resource = context.getResource("classpath:users.sql");
        EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("utf-8"));
        ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);

        setUpIsDone = true;
    }

    @Test
    public void successCreateUserAndGetUser() {
        when(executionContextAccessor.getUserId()).thenReturn(1L);

        CreateUserCommand createUserCommand = new CreateUserCommand("aaa@gmail.com", "12345678", "name");
        createUserCommandService.handle(createUserCommand);

        val getUserResult = getUserQueryService.handle(new GetUserQuery());

        assertNotNull(getUserResult.getUser());
        assertEquals(1L, getUserResult.getUser().getId());
        assertEquals("aaa@gmail.com", getUserResult.getUser().getEmail());
        assertEquals("name", getUserResult.getUser().getName());
    }
}
