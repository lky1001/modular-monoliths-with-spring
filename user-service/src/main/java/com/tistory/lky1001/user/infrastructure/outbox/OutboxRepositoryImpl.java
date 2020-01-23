package com.tistory.lky1001.user.infrastructure.outbox;

import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Override
    public List<OutboxMessage> getAllMessage() {
        return StreamSupport.stream(this.jdbcAggregateTemplate.findAll(OutboxMessage.class)
                .spliterator(), false)
                .collect(Collectors.toList());
    }
}
