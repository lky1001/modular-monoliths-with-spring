package com.tistory.lky1001.user.infrastructure.outbox;

import an.awesome.pipelinr.Pipeline;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.lky1001.buildingblocks.domain.IDomainEvent;
import com.tistory.lky1001.buildingblocks.infrastructure.seedwork.DomainEventNotificationBase;
import com.tistory.lky1001.user.application.configuration.commands.ICommandService;
import com.tistory.lky1001.user.application.contracts.VoidCommandResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProcessOutboxCommandService implements ICommandService<ProcessOutboxCommand, VoidCommandResult> {

    private Pipeline userNotificationPipeline;

    private IOutboxRepository outboxRepository;

    public ProcessOutboxCommandService(Pipeline userNotificationPipeline, IOutboxRepository outboxRepository) {
        this.userNotificationPipeline = userNotificationPipeline;
        this.outboxRepository = outboxRepository;
    }

    @Override
    public VoidCommandResult handle(ProcessOutboxCommand command) {
        logger.debug("ProcessOutboxCommandService thread name - {}", Thread.currentThread().getName());
        List<OutboxMessage> messages = outboxRepository.getAllMessage();

        for (OutboxMessage message : messages) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Class<IDomainEvent> clazz = (Class<IDomainEvent>) Class.forName(message.getType());
                IDomainEvent domainEvent = objectMapper.readValue(message.getData(), clazz);

                userNotificationPipeline.send(new DomainEventNotificationBase<>(domainEvent));
            } catch (JsonProcessingException | ClassNotFoundException e) {
                throw new RuntimeException("Deserialize error.");
            }
        }
        return null;
    }
}
