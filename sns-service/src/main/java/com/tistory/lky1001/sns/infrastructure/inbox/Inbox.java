package com.tistory.lky1001.sns.infrastructure.inbox;

import com.tistory.lky1001.buildingblocks.infrastructure.inbox.IInbox;
import org.springframework.stereotype.Component;

@Component("snsInbox")
public class Inbox implements IInbox<InboxMessage> {

    private IInboxRepository inboxRepository;

    public Inbox(IInboxRepository inboxRepository) {
        this.inboxRepository = inboxRepository;
    }

    @Override
    public void add(InboxMessage message) {

    }
}
