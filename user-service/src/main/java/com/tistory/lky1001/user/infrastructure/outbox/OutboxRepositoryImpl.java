package com.tistory.lky1001.user.infrastructure.outbox;

import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OutboxRepositoryImpl implements IOutboxRepository {

    private JdbcAggregateTemplate jdbcAggregateTemplate;

    public OutboxRepositoryImpl(JdbcAggregateTemplate jdbcAggregateTemplate) {
        this.jdbcAggregateTemplate = jdbcAggregateTemplate;
    }

    @Override
    public void addMessage(OutboxMessage message) {
        this.jdbcAggregateTemplate.insert(message);
    }
}
