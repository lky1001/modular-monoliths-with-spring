package com.tistory.lky1001.sns.infrastructure.outbox;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("snsOutboxRepository")
public interface IOutboxRepository extends CrudRepository<OutboxMessage, String>, SnsOutboxRepository {

    @Query("select * from outbox_message where processed_date is null for update skip locked")
    List<OutboxMessage> getAllMessageToProcess();
}
