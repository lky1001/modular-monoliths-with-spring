package com.tistory.lky1001.user.domain.users;

import com.tistory.lky1001.buildingblocks.domain.DomainValidationException;
import com.tistory.lky1001.buildingblocks.infrastructure.chiper.AES256Cipher;
import com.tistory.lky1001.buildingblocks.infrastructure.chiper.ICipherManager;
import com.tistory.lky1001.user.application.authorization.IPasswordManager;
import com.tistory.lky1001.user.application.authorization.PasswordManager;
import com.tistory.lky1001.user.domain.DomainRegistry;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

public class UserTest {

    @Mock
    private ApplicationContext applicationContext;

    private DomainRegistry domainRegistry;

    private IPasswordManager passwordManager = new PasswordManager(PasswordEncoderFactories.createDelegatingPasswordEncoder());

    private ICipherManager aes256Cipher = new AES256Cipher("B1B9A19C3F1A9QQO9REQ3FV928RDS#AA");

    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(applicationContext.getBean("passwordManager")).thenReturn(passwordManager);
        when(applicationContext.getBean("cipherManager")).thenReturn(aes256Cipher);

        domainRegistry = new DomainRegistry();
        domainRegistry.setApplicationContext(applicationContext);
    }

    @Test
    public void successCreateNewUser() {
        user = User.createUser("test@gmail.com", "password", "myname");

        assertEquals(aes256Cipher.encode("test@gmail.com"), User.getEncodedEmail(user.getEmail()));
        assertEquals("test@gmail.com", user.getEmail());
        assertTrue(passwordManager.matches("password", user.getEncryptedPassword()));
        assertEquals("myname", user.getName());
    }

    @Test(expected = DomainValidationException.class)
    public void failureCreateNewUserWhenEmalIsNull() {
        user = User.createUser(null, "password", "myname");

        fail("DomainValidationException has to be thrown");
    }

    @Test(expected = DomainValidationException.class)
    public void failureCreateNewUserWhenPasswordIsNull() {
        user = User.createUser("test@gmail.com", null, "myname");

        fail("DomainValidationException has to be thrown");
    }

    @Test(expected = DomainValidationException.class)
    public void failureCreateNewUserWhenNameIsNull() {
        user = User.createUser("test@gmail.com", "password", null);

        fail("DomainValidationException has to be thrown");
    }

    @Test
    public void successAddRole() {
        user = User.createUser("test@gmail.com", "password", "myname");

        Role role = new Role("ROLE_ADMIN", "admin user");

        user.addRole(role);

        assertEquals(1, user.getRoleIds().size());
        assertEquals(0, user.getRoleIds().toArray()[0]);
    }
}
