package com.tistory.lky1001.sns.infrastructure.eventbus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.AbstractIntegrationEvent;
import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.GuavaIntegrationEventService;
import com.tistory.lky1001.buildingblocks.infrastructure.inbox.IInbox;
import com.tistory.lky1001.sns.infrastructure.inbox.InboxMessage;
import com.tistory.lky1001.sns.infrastructure.inbox.ProcessInboxCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component("snsIntegrationEventService")
public class IntegrationEventService extends GuavaIntegrationEventService<AbstractIntegrationEvent> {

    private IInbox<InboxMessage> inbox;

    private ObjectMapper objectMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    public IntegrationEventService(@Qualifier("snsInbox") IInbox<InboxMessage> inbox, ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
        Assert.notNull(inbox, "Inbox is required.");
        this.inbox = inbox;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void handleEvent(AbstractIntegrationEvent event) {
        logger.debug("{} - handleEvent {}", event.getClass().getSimpleName(), Thread.currentThread().getName());

        String type = event.getClass().getName();
        String data = null;

        try {
            data = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Serialize error.");
        }

        applicationEventPublisher.publishEvent(new ProcessInboxCommand());
        inbox.add(new InboxMessage(event.getId(), event.getOccurredOn(), type, data));
    }
}
