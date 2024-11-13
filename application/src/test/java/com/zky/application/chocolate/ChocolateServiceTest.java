package com.zky.application.chocolate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {ChocolateService.class})
public class ChocolateServiceTest {

    @Autowired
    private ChocolateService chocolateService;

    @MockBean
    private ChocolateRepository chocolateRepository;

    @Test
    void test_getChocolate() {
        Chocolate expected = new Chocolate(2, "awesome chocolate");
        when(chocolateRepository.createChocolate()).thenReturn(expected);

        Chocolate chocolate = chocolateService.getChocolate();
        assertEquals(expected, chocolate);
    }
}
