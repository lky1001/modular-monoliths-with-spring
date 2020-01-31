package com.tistory.lky1001.buildingblocks.infrastructure.seedwork;

import an.awesome.pipelinr.Notification;

import java.lang.reflect.ParameterizedType;

public interface IDomainEventNotificationService<T extends IDomainEventNotification> extends Notification.Handler<T> {

    @Override
    default boolean matches(T notification) {
        ParameterizedType handlerParameterizedType = ((ParameterizedType) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0]);
        return ((Class<?>) handlerParameterizedType.getActualTypeArguments()[0]).isAssignableFrom(notification.getDomainEvent().getClass());
    }
}
