package com.tistory.lky1001.sns.infrastructure.outbox;

import an.awesome.pipelinr.Pipeline;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.lky1001.buildingblocks.domain.IDomainEvent;
import com.tistory.lky1001.buildingblocks.infrastructure.outbox.IOutbox;
import com.tistory.lky1001.buildingblocks.infrastructure.seedwork.DomainEventNotificationBase;
import com.tistory.lky1001.sns.application.configuration.commands.ICommandService;
import com.tistory.lky1001.sns.application.contracts.VoidCommandResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Service("snsProcessOutboxCommandService")
public class ProcessOutboxCommandService implements ICommandService<ProcessOutboxCommand, VoidCommandResult> {

    private Pipeline snsNotificationPipeline;
    private IOutbox<OutboxMessage> outbox;
    private ObjectMapper objectMapper;

    public ProcessOutboxCommandService(Pipeline snsNotificationPipeline, IOutbox<OutboxMessage> outbox) {
        Assert.notNull(snsNotificationPipeline, "SnsNotificationPipeline is required.");
        Assert.notNull(outbox, "SnsOutbox is required.");
        this.snsNotificationPipeline = snsNotificationPipeline;
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

                snsNotificationPipeline.send(new DomainEventNotificationBase<>(domainEvent));

                outbox.processedMessage(message);
                logger.debug("{} - Update ProcessOutboxCommandService", Thread.currentThread().getName());
            } catch (JsonProcessingException | ClassNotFoundException e) {
                throw new RuntimeException("Deserialize error.");
            }
        }

        return VoidCommandResult.Void();
    }
}
