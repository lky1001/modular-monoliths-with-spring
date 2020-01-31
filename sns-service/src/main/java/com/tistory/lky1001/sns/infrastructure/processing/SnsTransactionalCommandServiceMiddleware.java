package com.tistory.lky1001.sns.infrastructure.processing;

import an.awesome.pipelinr.Command;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(2)
public class SnsTransactionalCommandServiceMiddleware implements Command.Middleware {

    @Transactional(value = "snsTransactionManager", rollbackFor = Exception.class)
    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        return next.invoke();
    }
}
