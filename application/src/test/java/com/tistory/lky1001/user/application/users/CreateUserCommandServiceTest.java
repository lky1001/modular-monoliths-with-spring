package com.tistory.lky1001.user.application.users;

import com.tistory.lky1001.Application;
import com.tistory.lky1001.buildingblocks.infrastructure.chiper.AES256Cipher;
import com.tistory.lky1001.buildingblocks.infrastructure.chiper.ICipherManager;
import com.tistory.lky1001.user.application.authorization.IPasswordManager;
import com.tistory.lky1001.user.application.authorization.PasswordManager;
import com.tistory.lky1001.user.application.users.createuser.CreateUserCommand;
import com.tistory.lky1001.user.application.users.createuser.CreateUserCommandService;
import com.tistory.lky1001.user.application.users.createuser.CreateUserResult;
import com.tistory.lky1001.user.domain.users.IRoleRepository;
import com.tistory.lky1001.user.domain.users.IUserRepository;
import com.tistory.lky1001.user.domain.users.Role;
import com.tistory.lky1001.user.domain.users.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class CreateUserCommandServiceTest {

    private static boolean setUpIsDone = false;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRepository userRepository;

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
    public void successCreateUserWithRole() {
        CreateUserCommand createUserCommand = new CreateUserCommand("aaa@gmail.com", "12345678", "name");

        CreateUserResult createUserResult = createUserCommandService.handle(createUserCommand);

        assertTrue(createUserResult.getResult());
    }
}