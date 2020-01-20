package com.tistory.lky1001.user.application.configuration.queries;

import com.tistory.lky1001.user.application.contracts.IQuery;
import com.tistory.lky1001.user.application.contracts.IResult;

import java.lang.reflect.ParameterizedType;

public interface IQueryService<T extends IQuery, R extends IResult> {

    default boolean isType(IQuery query) {
        Class<T> type = (Class<T>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];

        return type.isInstance(query);
    }

    R handle(T query);
}
