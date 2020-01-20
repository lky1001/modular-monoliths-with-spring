package com.tistory.lky1001;

import com.tistory.lky1001.buildingblocks.application.IExecutionContextAccessor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SpringExecutionContextAccessor implements IExecutionContextAccessor {

    @Override
    public int getUserId() {
        return 0;
    }

    @Override
    public UUID getCorrelationId() {
        return null;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}
