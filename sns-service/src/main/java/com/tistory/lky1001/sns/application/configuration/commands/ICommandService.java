package com.tistory.lky1001.sns.application.configuration.commands;

import com.tistory.lky1001.sns.application.contracts.ICommand;
import com.tistory.lky1001.sns.application.contracts.IResult;

import java.lang.reflect.ParameterizedType;

public interface ICommandService<T extends ICommand, R extends IResult> {

    default boolean isType(ICommand command) {
        Class<T> type = (Class<T>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];

        return type.isInstance(command);
    }

    R handle(T command);
}
