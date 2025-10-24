package com.cinehub.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(
        basePackages = "com.cinehub",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Controller.class
        )
)
@Import(PersistenceConfig.class)
public class AppConfig {
    // Configuration services, repositories, etc.
}