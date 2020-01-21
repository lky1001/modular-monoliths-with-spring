package com.tistory.lky1001.user.domain.users;

import com.tistory.lky1001.user.application.contracts.AggregateRoot;
import com.tistory.lky1001.user.domain.DomainRegistry;
import com.tistory.lky1001.user.domain.users.validator.EmailValidator;
import com.tistory.lky1001.user.domain.users.validator.NameValidator;
import com.tistory.lky1001.user.domain.users.validator.PasswordValidator;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public class User extends AggregateRoot<User> {

    @Id
    private long id;

    private String email;

    private String password;

    private String name;

    @MappedCollection(idColumn = "user_id")
    private Set<RoleRef> roles = new HashSet<>();

    private Date created;

    private Date updated;

    private User(String email, String password, String name) {
        this.protectedEmail(email);
        this.setName(name);
        this.protectPassword("", password);
        this.setCreated(new Date());
    }

    public static User createUser(String email, String password, String name) {
        return new User(email, password, name);
    }

    public void changePassword(String currentPassword, String changedPassword) {
        this.protectPassword(currentPassword, changedPassword);
        this.setUpdated(new Date());
    }

    public void addRole(Role role) {
        this.roles.add(new RoleRef(role.getId()));
    }

    public Set<Integer> getRoleIds() {
        return this.roles.stream()
                .map(RoleRef::getRoleId).collect(Collectors.toSet());
    }

    private void protectedEmail(String email) {
        validationCheck(new EmailValidator(email));

        this.setEmail(this. encodedValue(email));
    }

    private void setEmail(String encryptedEmail) {
        this.email = encryptedEmail;
    }

    private void setName(String name) {
        validationCheck(new NameValidator(name));

        this.name = name;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void protectPassword(String currentPassword, String changedPassword) {
        validationCheck(new PasswordValidator(currentPassword, changedPassword));

        this.setPassword(this.encryptedValue(changedPassword));
    }

    private String decodeValue(String encodedTextEmail) {
        String decodedValue = DomainRegistry.cipherManager()
                .decode(encodedTextEmail);

        return decodedValue;
    }

    private String encodedValue(String plainTextEmail) {
        String encodedValue = DomainRegistry.cipherManager()
                .encode(plainTextEmail);

        return encodedValue;
    }

    private String encryptedValue(String plainTextPassword) {
        String encryptedValue = DomainRegistry.passwordManager()
                .encode(plainTextPassword);

        return encryptedValue;
    }

    public static String getEncodedEmail(String plainTextEmail) {
        return DomainRegistry.cipherManager()
                .encode(plainTextEmail);
    }

    private void setCreated(Date created) {
        this.created = created;
    }

    private void setUpdated(Date updated) {
        this.updated = updated;
    }

    public long getId() {
        return this.id;
    }

    public String getEmail() {
        return decodeValue(this.email);
    }

    public String getEncryptedPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }
}
