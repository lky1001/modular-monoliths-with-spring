package com.tistory.lky1001.sns.infrastructure.inbox;

import an.awesome.pipelinr.Pipeline;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.lky1001.buildingblocks.domain.IDomainEvent;
import com.tistory.lky1001.buildingblocks.infrastructure.seedwork.DomainEventNotificationBase;
import com.tistory.lky1001.sns.application.configuration.commands.ICommandService;
import com.tistory.lky1001.sns.application.contracts.VoidCommandResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("snsProcessInboxCommandService")
public class ProcessInboxCommandService implements ICommandService<ProcessInboxCommand, VoidCommandResult> {

    private Pipeline userNotificationPipeline;

    private IInboxRepository inboxRepository;

    public ProcessInboxCommandService(Pipeline userNotificationPipeline, IInboxRepository inboxRepository) {
        this.userNotificationPipeline = userNotificationPipeline;
        this.inboxRepository = inboxRepository;
    }

    @Override
    public VoidCommandResult handle(ProcessInboxCommand command) {
        logger.debug("ProcessInboxCommandService thread name - {}", Thread.currentThread().getName());
        List<InboxMessage> messages = inboxRepository.getAllMessage();

        for (InboxMessage message : messages) {
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
