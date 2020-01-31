package com.tistory.lky1001.sns.infrastructure.domain.users;

import com.tistory.lky1001.sns.domain.users.User;

public interface SnsUserRepository {

    void addUser(User user);
}
