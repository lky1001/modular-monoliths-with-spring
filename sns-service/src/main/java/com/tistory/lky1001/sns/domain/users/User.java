package com.tistory.lky1001.sns.domain.users;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class User {

    @Id
    private long userId;

    private String name;

    private Date created;

    private Date updated;

    private User(long userId, String name, Date created) {
        this.setUserId(userId);
        this.setName(name);
        this.setCreated(created);
    }

    public static User createUser(long userId, String name, Date created) {
        return new User(userId, name, created);
    }

    private void setUserId(long userId) {
        this.userId = userId;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setCreated(Date created) {
        this.created = created;
    }

    private void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void changeName(String changedName, Date updated) {
        this.name = changedName;
        this.setUpdated(updated);
    }
}
