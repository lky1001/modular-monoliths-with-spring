package com.tistory.lky1001.user.application.domain.users;

import com.tistory.lky1001.buildingblocks.domain.DomainValidationException;
import com.tistory.lky1001.user.application.contracts.AggregateRoot;
import com.tistory.lky1001.user.application.domain.users.validator.EmailValidator;
import com.tistory.lky1001.user.application.domain.users.validator.NameValidator;
import com.tistory.lky1001.user.application.domain.users.validator.PasswordValidator;
import org.springframework.data.annotation.Id;

public class User extends AggregateRoot<User> {

    @Id
    private long id;

    private String email;

    private String password;

    private String name;

    private User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User createUser(String email, String password, String name) throws DomainValidationException {
        validationCheck(new EmailValidator(email));
        validationCheck(new PasswordValidator(password));
        validationCheck(new NameValidator(name));

        return new User(email, password, name);
    }
}
