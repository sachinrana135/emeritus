package com.sachin.emeritus.user.service.impl;

import com.sachin.emeritus.commonlib.security.JwtUtil;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditingService implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String userId;
        try {
            userId = JwtUtil.getUserId();
        } catch (Exception e) {
            userId = "UNKNOWN";
        }
        return Optional.of(userId);
    }
}
