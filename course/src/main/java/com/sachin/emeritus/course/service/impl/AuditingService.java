package com.sachin.emeritus.course.service.impl;

import com.sachin.emeritus.commonlib.security.JwtUtil;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditingService implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(JwtUtil.getUserId());
    }
}
