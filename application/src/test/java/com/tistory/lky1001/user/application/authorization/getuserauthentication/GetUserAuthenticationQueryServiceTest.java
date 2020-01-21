package com.tistory.lky1001.user.application.authorization.getuserauthentication;

import com.tistory.lky1001.Application;
import com.tistory.lky1001.user.application.authorization.IPasswordManager;
import com.tistory.lky1001.user.domain.DomainRegistry;
import com.tistory.lky1001.user.domain.users.IRoleRepository;
import com.tistory.lky1001.user.domain.users.IUserRepository;
import com.tistory.lky1001.user.domain.users.Role;
import com.tistory.lky1001.user.domain.users.User;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DataSource dataSource;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRepository userRepository;

    private GetUserAuthenticationQueryService getUserAuthenticationQueryService;

    @Before
    public void setUp() throws SQLException {
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
    public void successCreateUserAndGetUserAuthentication() {
        User user = User.createUser("test@gmail.com", "test1234", "test");

        Role role = roleRepository.findById(2).orElseThrow();

        user.addRole(role);

        userRepository.save(user);

        val getUserAuthenticationResult = getUserAuthenticationQueryService.handle(new GetUserAuthenticationQuery("test@gmail.com"));

        IPasswordManager passwordManager = DomainRegistry.passwordManager();

        assertTrue(getUserAuthenticationResult.isAuthenticated());
        assertEquals("test@gmail.com", getUserAuthenticationResult.getUserAuthentication().getEmail());
        assertTrue(passwordManager.matches("test1234", getUserAuthenticationResult.getUserAuthentication().getPassword()));
        assertEquals(1, getUserAuthenticationResult.getUserAuthentication().getRoles().size());
        assertEquals("ROLE_USER", getUserAuthenticationResult.getUserAuthentication().getRoles().get(0).getName());
    }

    @Test
    public void successGetUserAuthenticationFailure() {
        User user = User.createUser("test1@gmail.com", "test1234", "test");

        Role role = roleRepository.findById(2).orElseThrow();

        user.addRole(role);

        userRepository.save(user);

        val getUserAuthenticationResult = getUserAuthenticationQueryService.handle(new GetUserAuthenticationQuery("test123@gmail.com"));

        assertFalse(getUserAuthenticationResult.isAuthenticated());
    }
}
