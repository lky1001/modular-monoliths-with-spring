package com.tistory.lky1001.sns.infrastructure.inbox;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("snsInboxRepository")
public interface IInboxRepository extends CrudRepository<InboxMessage, String>, CustomInboxRepository {

    @Query("select * from inbox_message where processed_date is null for update skip locked")
    List<InboxMessage> getAllMessageToProcess();
}
