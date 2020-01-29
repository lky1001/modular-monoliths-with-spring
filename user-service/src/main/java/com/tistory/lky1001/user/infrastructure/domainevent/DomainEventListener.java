package com.tistory.lky1001.user.infrastructure.domainevent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.lky1001.buildingblocks.domaineventlistener.IDomainEventListener;
import com.tistory.lky1001.buildingblocks.infrastructure.outbox.IOutbox;
import com.tistory.lky1001.user.domain.AbstractDomainEvent;
import com.tistory.lky1001.user.infrastructure.outbox.OutboxMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component("userDomainEventListener")
public class DomainEventListener implements IDomainEventListener<AbstractDomainEvent> {

    private IOutbox<OutboxMessage> outbox;
    private ObjectMapper objectMapper;

    public DomainEventListener(IOutbox<OutboxMessage> outbox) {
        this.outbox = outbox;
        this.objectMapper = new ObjectMapper();
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    @Override
    public void handleEvent(AbstractDomainEvent event) {
        String type = event.getClass().getName();
        String data = null;

        try {
            data = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Serialize error.");
        }

        outbox.add(new OutboxMessage(event.getId(), event.occurredOn(), type, data));
    }
}
