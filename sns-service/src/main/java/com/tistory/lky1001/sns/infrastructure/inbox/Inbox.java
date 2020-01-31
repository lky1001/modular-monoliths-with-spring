package com.tistory.lky1001.sns.infrastructure.inbox;

import com.tistory.lky1001.buildingblocks.infrastructure.inbox.IInbox;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component("snsInbox")
public class Inbox implements IInbox<InboxMessage> {

    private IInboxRepository inboxRepository;

    public Inbox(@Qualifier("snsInboxRepository") IInboxRepository inboxRepository) {
        Assert.notNull(inboxRepository, "SnsInboxRepository is required.");
        this.inboxRepository = inboxRepository;
    }

    @Override
    public void add(InboxMessage message) {
        inboxRepository.addMessage(message);
    }

    @Override
    public List<InboxMessage> getAllMessageToProcess() {
        return inboxRepository.getAllMessageToProcess();
    }

    @Override
    public void processedMessage(InboxMessage message) {
        message.processed();
        inboxRepository.save(message);
    }
}
