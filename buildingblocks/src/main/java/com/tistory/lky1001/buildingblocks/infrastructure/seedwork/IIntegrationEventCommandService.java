package com.tistory.lky1001.buildingblocks.infrastructure.seedwork;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Voidy;

import java.lang.reflect.ParameterizedType;

public interface IIntegrationEventCommandService<C extends IIntegrationEventCommand<?, R>, R extends Voidy> extends Command.Handler<C, R> {

    @Override
    default boolean matches(C command) {
        ParameterizedType handlerParameterizedType = ((ParameterizedType) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0]);
        return ((Class<?>) handlerParameterizedType.getActualTypeArguments()[0]).isAssignableFrom(command.getIntegrationEvent().getClass());
    }
}
