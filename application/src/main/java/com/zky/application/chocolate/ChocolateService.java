package com.zky.application.chocolate;

import org.springframework.stereotype.Component;

@Component
public class ChocolateService {

    private final ChocolateRepository chocolateRepository;

    public ChocolateService(ChocolateRepository chocolateRepository) {
        this.chocolateRepository = chocolateRepository;
    }

    public Chocolate getChocolate() {
        return chocolateRepository.createChocolate();
    }
}
