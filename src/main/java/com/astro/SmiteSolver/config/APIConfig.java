package com.astro.SmiteSolver.config;

import com.astro.smitebasic.objects.session.SessionRepository;
import com.astro.smitebasic.objects.session.SessionService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.astro.smitebasic.api", "com.astro.smitebasic.objects.session"},
        basePackageClasses = {SessionRepository.class, SessionService.class})
public class APIConfig {
}
