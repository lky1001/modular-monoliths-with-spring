package com.tistory.lky1001.buildingblocks.infrastructure.eventbus;

import com.google.common.eventbus.EventBus;

public class GuavaEventBus implements IEventsBus {

    public static final ThreadLocal<Exception> EVENT_BUS_EXCEPTION_HOLDER = new ThreadLocal<>();

    private EventBus eventBus = new EventBus((exception, context) -> {
        EVENT_BUS_EXCEPTION_HOLDER.remove();
        EVENT_BUS_EXCEPTION_HOLDER.set(new RuntimeException(exception));
    });

    @Override
    public <T extends AbstractIntegrationEvent> void publish(T event) {
        eventBus.post(event);

        try {
            if (null != EVENT_BUS_EXCEPTION_HOLDER.get()) {
                throw new RuntimeException(EVENT_BUS_EXCEPTION_HOLDER.get());
            }
        } finally {
            EVENT_BUS_EXCEPTION_HOLDER.remove();
        }
    }

    @Override
    public <T extends AbstractIntegrationEvent> void subscribe(IIntegrationEventService<T> service) {
        eventBus.register(service);
    }
}
