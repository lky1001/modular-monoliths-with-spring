package com.tistory.lky1001;

import com.tistory.lky1001.buildingblocks.application.IExecutionContextAccessor;
import com.tistory.lky1001.configuration.security.CustomUserDetails;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

@Component
public class SpringExecutionContextAccessor implements IExecutionContextAccessor {

    @Override
    public Long getUserId() {
        val authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        }

        val principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getId();
        }

        return null;
    }

    @Override
    public String getAuthorization() {
        val currentRequestAttributes = RequestContextHolder.currentRequestAttributes();

        if (currentRequestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) currentRequestAttributes)
                    .getRequest().getHeader("Authorization");
        }

        return null;
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
