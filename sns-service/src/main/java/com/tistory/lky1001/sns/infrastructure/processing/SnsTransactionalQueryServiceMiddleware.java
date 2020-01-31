package com.tistory.lky1001.sns.infrastructure.processing;

import an.awesome.pipelinr.Command;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(1)
public class SnsTransactionalQueryServiceMiddleware implements Command.Middleware {

    @Transactional(value = "snsTransactionManager", readOnly = true)
    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        return next.invoke();
    }
}
