package com.tistory.lky1001.buildingblocks.infrastructure.seedwork;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Voidy;

public interface IIntegrationEventCommand<T, R extends Voidy> extends Command<R> {

    T getIntegrationEvent();

    String getId();
}
