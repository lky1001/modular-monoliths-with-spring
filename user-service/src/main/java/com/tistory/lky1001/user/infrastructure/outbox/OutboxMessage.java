package com.tistory.lky1001.user.infrastructure.outbox;

import com.tistory.lky1001.buildingblocks.infrastructure.outbox.AbstractOutboxMessage;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("outbox_message")
public class OutboxMessage extends AbstractOutboxMessage {

    @Id
    private String id;

    public OutboxMessage(String id, Date occurredOn, String type, String data) {
        super(occurredOn, type, data);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
