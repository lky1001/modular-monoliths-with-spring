package com.tistory.lky1001.buildingblocks.infrastructure.inbox;

import java.util.Date;

public abstract class AbstractInboxMessage {

    protected Date occurredOn;

    protected String type;

    protected String data;

    protected Date processedDate;

    public AbstractInboxMessage(Date occurredOn, String type, String data) {
        this.occurredOn = occurredOn;
        this.type = type;
        this.data = data;
    }

    public Date getOccurredOn() {
        return occurredOn;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public Date getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(Date processedDate) {
        this.processedDate = processedDate;
    }

    public void processed() {
        processedDate = new Date();
    }
}
