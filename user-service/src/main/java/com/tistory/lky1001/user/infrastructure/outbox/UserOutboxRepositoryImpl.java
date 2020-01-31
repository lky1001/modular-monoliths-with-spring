package com.tistory.lky1001.user.infrastructure.outbox;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;

public class UserOutboxRepositoryImpl implements UserOutboxRepository {

    private JdbcAggregateTemplate jdbcAggregateTemplate;

    public UserOutboxRepositoryImpl(@Qualifier("userJdbcAggregateTemplate") JdbcAggregateTemplate jdbcAggregateTemplate) {
        this.jdbcAggregateTemplate = jdbcAggregateTemplate;
    }

    @Override
    public void addMessage(OutboxMessage outboxMessage) {
        this.jdbcAggregateTemplate.insert(outboxMessage);
    }
}
