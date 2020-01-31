package com.tistory.lky1001.sns.infrastructure.outbox;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;

public class SnsOutboxRepositoryImpl implements SnsOutboxRepository {

    private JdbcAggregateTemplate jdbcAggregateTemplate;

    public SnsOutboxRepositoryImpl(@Qualifier("snsJdbcAggregateTemplate") JdbcAggregateTemplate jdbcAggregateTemplate) {
        this.jdbcAggregateTemplate = jdbcAggregateTemplate;
    }

    @Override
    public void addMessage(OutboxMessage outboxMessage) {
        this.jdbcAggregateTemplate.insert(outboxMessage);
    }
}
