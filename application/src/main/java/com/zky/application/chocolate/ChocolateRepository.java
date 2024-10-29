package com.zky.application.chocolate;

import org.springframework.stereotype.Component;

@Component
public class ChocolateRepository {

    public Chocolate createChocolate() {
        return new Chocolate(1, "Milk Chocolate");
    }
}
