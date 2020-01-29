package com.tistory.lky1001.sns.infrastructure.inbox;

import com.tistory.lky1001.buildingblocks.infrastructure.inbox.AbstractInboxMessage;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("inbox_message")
public class InboxMessage extends AbstractInboxMessage {

    @Id
    private String id;

    public InboxMessage(String id, Date occurredOn, String type, String data) {
        super(occurredOn, type, data);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
