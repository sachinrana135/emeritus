package com.sachin.emeritus.user.config;

import com.sachin.emeritus.user.service.impl.AuditingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;


@Configuration
public class AppConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditingService();
    }
}
