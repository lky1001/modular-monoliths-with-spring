package com.tistory.lky1001.buildingblocks.application;

import java.util.UUID;

public interface IExecutionContextAccessor {

    int getUserId();

    UUID getCorrelationId();

    boolean isAvailable();
}
