package com.zky.application.chocolate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {ChocolateService.class})
public class ChocolateServiceTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ChocolateService chocolateService;

    @MockitoBean
    private ChocolateRepository chocolateRepository;

    @Test
    void test_printAllBeans() {
        String[] beanNames = context.getBeanDefinitionNames();
        System.out.println("=== " + beanNames.length + " Beans in ApplicationContext ===");
        for (String name : beanNames) {
            Object bean = context.getBean(name);
            System.out.println(name + " -> " + bean.getClass().getName());
        }
    }

    @Test
    void test_getChocolate() {
        Chocolate expected = new Chocolate(2, "awesome chocolate");
        when(chocolateRepository.createChocolate()).thenReturn(expected);

        Chocolate chocolate = chocolateService.getChocolate();
        assertEquals(expected, chocolate);
    }
}
