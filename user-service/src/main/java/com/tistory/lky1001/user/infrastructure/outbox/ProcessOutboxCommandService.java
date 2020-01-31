package com.tistory.lky1001.user.infrastructure.outbox;

import an.awesome.pipelinr.Pipeline;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.lky1001.buildingblocks.domain.IDomainEvent;
import com.tistory.lky1001.buildingblocks.infrastructure.outbox.IOutbox;
import com.tistory.lky1001.buildingblocks.infrastructure.seedwork.DomainEventNotificationBase;
import com.tistory.lky1001.user.application.configuration.commands.ICommandService;
import com.tistory.lky1001.user.application.contracts.VoidCommandResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Service("userProcessOutboxCommandService")
public class ProcessOutboxCommandService implements ICommandService<ProcessOutboxCommand, VoidCommandResult> {

    private Pipeline userNotificationPipeline;
    private IOutbox<OutboxMessage> outbox;
    private ObjectMapper objectMapper;

    public ProcessOutboxCommandService(Pipeline userNotificationPipeline, IOutbox<OutboxMessage> outbox) {
        Assert.notNull(userNotificationPipeline, "UserNotificationPipeline is required.");
        Assert.notNull(outbox, "UserOutbox is required.");
        this.userNotificationPipeline = userNotificationPipeline;
        this.outbox = outbox;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public VoidCommandResult handle(ProcessOutboxCommand command) {
        logger.debug("{} - Start ProcessOutboxCommandService", Thread.currentThread().getName());
        List<OutboxMessage> messages = outbox.getAllMessageToProcess();

        for (OutboxMessage message : messages) {
            try {
                Class<IDomainEvent> clazz = (Class<IDomainEvent>) Class.forName(message.getType());
                IDomainEvent domainEvent = objectMapper.readValue(message.getData(), clazz);

                userNotificationPipeline.send(new DomainEventNotificationBase<>(domainEvent));

                outbox.processedMessage(message);
                logger.debug("{} - Update ProcessOutboxCommandService", Thread.currentThread().getName());
            } catch (JsonProcessingException | ClassNotFoundException e) {
                throw new RuntimeException("Deserialize error.");
            }
        }

        return VoidCommandResult.Void();
    }
}
