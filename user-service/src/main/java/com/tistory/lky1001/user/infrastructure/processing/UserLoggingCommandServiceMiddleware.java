package com.tistory.lky1001.user.infrastructure.processing;

import an.awesome.pipelinr.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(1)
public class UserLoggingCommandServiceMiddleware implements Command.Middleware {

    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        logger.debug("start {}", command.getClass().getSimpleName());

        R response = next.invoke();

        logger.debug("finish {}", command.getClass().getSimpleName());

        return response;
    }
}
