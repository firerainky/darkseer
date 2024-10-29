package com.zky.application.chocolate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public ChocolateRepository chocolateRepository() {
        return new ChocolateRepository();
    }

    @Bean
    public ChocolateService chocolateService(ChocolateRepository chocolateRepository) {
        return new ChocolateService(chocolateRepository);
    }
}
