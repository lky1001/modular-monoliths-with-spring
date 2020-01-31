package com.tistory.lky1001.sns.infrastructure.inbox;

import an.awesome.pipelinr.Pipeline;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.lky1001.buildingblocks.infrastructure.eventbus.AbstractIntegrationEvent;
import com.tistory.lky1001.buildingblocks.infrastructure.inbox.IInbox;
import com.tistory.lky1001.buildingblocks.infrastructure.seedwork.IntegrationEventCommandBase;
import com.tistory.lky1001.sns.application.configuration.commands.ICommandService;
import com.tistory.lky1001.sns.application.contracts.VoidCommandResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Service("snsProcessInboxCommandService")
public class ProcessInboxCommandService implements ICommandService<ProcessInboxCommand, VoidCommandResult> {

    private Pipeline snsIntegrationCommandPipeline;
    private IInbox<InboxMessage> inbox;
    private ObjectMapper objectMapper;

    public ProcessInboxCommandService(Pipeline snsIntegrationCommandPipeline, IInbox<InboxMessage> inbox) {
        Assert.notNull(snsIntegrationCommandPipeline, "SnsIntegrationCommandPipeline is required.");
        Assert.notNull(inbox, "SnsInbox is required.");
        this.snsIntegrationCommandPipeline = snsIntegrationCommandPipeline;
        this.inbox = inbox;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public VoidCommandResult handle(ProcessInboxCommand command) {
        logger.debug("{} - Start ProcessInboxCommandService", Thread.currentThread().getName());
        List<InboxMessage> messages = inbox.getAllMessageToProcess();

        for (InboxMessage message : messages) {
            try {
                Class<AbstractIntegrationEvent> clazz = (Class<AbstractIntegrationEvent>) Class.forName(message.getType());
                AbstractIntegrationEvent integrationEvent = objectMapper.readValue(message.getData(), clazz);

                snsIntegrationCommandPipeline.send(new IntegrationEventCommandBase<>(integrationEvent));

                inbox.processedMessage(message);
            } catch (JsonProcessingException | ClassNotFoundException e) {
                throw new RuntimeException("Deserialize error.");
            }
        }

        return VoidCommandResult.Void();
    }
}
