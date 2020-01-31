package com.tistory.lky1001.sns.infrastructure.domain.users;

import com.tistory.lky1001.sns.domain.users.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;

public class SnsUserRepositoryImpl implements SnsUserRepository {

    private JdbcAggregateTemplate jdbcAggregateTemplate;

    public SnsUserRepositoryImpl(@Qualifier("snsJdbcAggregateTemplate") JdbcAggregateTemplate jdbcAggregateTemplate) {
        this.jdbcAggregateTemplate = jdbcAggregateTemplate;
    }

    @Override
    public void addUser(User user) {
        jdbcAggregateTemplate.insert(user);
    }
}
