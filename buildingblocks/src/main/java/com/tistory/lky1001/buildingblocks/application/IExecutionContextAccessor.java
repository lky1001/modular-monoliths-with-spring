package com.tistory.lky1001.buildingblocks.application;

import java.util.UUID;

public interface IExecutionContextAccessor {

    Long getUserId();

    String getAuthorization();

    UUID getCorrelationId();

    boolean isAvailable();
}
