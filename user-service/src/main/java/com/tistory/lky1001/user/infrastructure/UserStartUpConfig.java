package com.tistory.lky1001.user.infrastructure;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import com.tistory.lky1001.buildingblocks.infrastructure.chiper.AES256Cipher;
import com.tistory.lky1001.buildingblocks.infrastructure.chiper.ICipherManager;
import com.tistory.lky1001.user.application.authorization.IPasswordManager;
import com.tistory.lky1001.user.application.authorization.PasswordManager;
import com.tistory.lky1001.user.application.configuration.commands.ICommandService;
import com.tistory.lky1001.user.application.configuration.queries.IQueryService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Stream;

@Configuration
public class UserStartUpConfig {

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean("passwordManager")
    public IPasswordManager passwordManager(@Autowired PasswordEncoder passwordEncoder) {
        return new PasswordManager(passwordEncoder);
    }

    @Bean("cipherManager")
    public ICipherManager cipherManager(@Value("${security.salt}") String salt) {
        return new AES256Cipher(salt);
    }

    @Bean("userCommandPipeline")
    public Pipeline userCommandPipeline(ObjectProvider<Command.Handler> commandHandlers, @Qualifier("loggingCommandServiceMiddleware") Command.Middleware loggingCommandServiceMiddleware,
            @Qualifier("transactionalCommandServiceMiddleware") Command.Middleware transactionalCommandServiceMiddleware) {
        return new Pipelinr()
                .with(() -> commandHandlers.stream().filter(item -> item instanceof ICommandService))
                .with(() -> Stream.of(loggingCommandServiceMiddleware, transactionalCommandServiceMiddleware));
    }

    @Bean("userQueryPipeline")
    public Pipeline userQueryPipeline(ObjectProvider<Command.Handler> commandHandlers, @Qualifier("transactionalQueryServiceMiddleware") Command.Middleware  transactionalQueryServiceMiddleware) {
        return new Pipelinr()
                .with(() -> commandHandlers.stream().filter(item -> item instanceof IQueryService))
                .with(() -> Stream.of(transactionalQueryServiceMiddleware));
    }
}
