package com.zky.application.chocolate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChocolateService {

    @Autowired
    private ChocolateRepository chocolateRepository;

    public Chocolate getChocolate() {
        return chocolateRepository.createChocolate();
    }
}
