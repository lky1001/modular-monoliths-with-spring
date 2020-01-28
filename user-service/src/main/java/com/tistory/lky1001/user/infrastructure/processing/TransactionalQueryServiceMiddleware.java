package com.tistory.lky1001.user.infrastructure.processing;

import an.awesome.pipelinr.Command;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("transactionalQueryServiceMiddleware")
@Order(1)
public class TransactionalQueryServiceMiddleware implements Command.Middleware {

    @Transactional(value = "userTransactionManager", readOnly = true)
    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        return next.invoke();
    }
}
