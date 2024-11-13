package com.zky.application.chocolate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {ChocolateService.class, ChocolateRepository.class})
public class ChocolateServiceTest {

    @Autowired
    private ChocolateService chocolateService;

    @Test
    void test_getChocolate() {
        Chocolate chocolate = chocolateService.getChocolate();
        System.out.println(chocolate);
    }
}
