package com.tistory.lky1001.sns.infrastructure.inbox;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository("snsIutboxRepository")
public class InboxRepositoryImpl implements IInboxRepository {

    private JdbcAggregateTemplate jdbcAggregateTemplate;

    public InboxRepositoryImpl(@Qualifier("snsJdbcAggregateTemplate") JdbcAggregateTemplate jdbcAggregateTemplate) {
        this.jdbcAggregateTemplate = jdbcAggregateTemplate;
    }

    @Override
    public void addMessage(InboxMessage message) {
        this.jdbcAggregateTemplate.insert(message);
    }

    @Override
    public List<InboxMessage> getAllMessage() {
        return StreamSupport.stream(this.jdbcAggregateTemplate.findAll(InboxMessage.class)
                .spliterator(), false)
                .collect(Collectors.toList());
    }
}
