package com.tistory.lky1001.sns.infrastructure.inbox;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;

public class CustomInboxRepositoryImpl implements CustomInboxRepository {

    private JdbcAggregateTemplate jdbcAggregateTemplate;

    public CustomInboxRepositoryImpl(@Qualifier("snsJdbcAggregateTemplate") JdbcAggregateTemplate jdbcAggregateTemplate) {
        this.jdbcAggregateTemplate = jdbcAggregateTemplate;
    }

    @Override
    public void addMessage(InboxMessage inboxMessage) {
        this.jdbcAggregateTemplate.insert(inboxMessage);
    }
}
