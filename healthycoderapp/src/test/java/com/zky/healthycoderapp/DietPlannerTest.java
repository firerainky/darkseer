package com.zky.healthycoderapp;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class DietPlannerTest {

    private DietPlanner dietPlanner;

    @BeforeEach
    void setUp() {
        dietPlanner = new DietPlanner(20, 30, 50);
    }

    @AfterEach
    void afterEach() {
        System.out.println("A unit test was finished.");
    }

    @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
    void should_ReturnCorrectDiatPlan_When_CorrectCoder() {
        // given
        Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
        DietPlan expected = new DietPlan(2202, 110, 73, 275);

        // when
        DietPlan actual = dietPlanner.calculateDiet(coder);

        // then
        assertAll(
                () -> assertEquals(expected.getCalories(), actual.getCalories()),
                () -> assertEquals(expected.getProtein(), actual.getProtein()),
                () -> assertEquals(expected.getFat(), actual.getFat()),
                () -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate())  
        );
    }

    @Test
    void should_CalculateDietPlanForFemaleCoder() {
        // given
        Coder coder = new Coder(1.65, 55.0, 30, Gender.FEMALE);
        DietPlan expectedDietPlan = new DietPlan(1674, 125, 83, 278);

        // when
        DietPlan actualDietPlan = dietPlanner.calculateDiet(coder);

        // then
        assertEquals(expectedDietPlan, actualDietPlan);
    }

    @Test
    void should_ThrowException_When_InvalidPercentageSum() {
        // given
        int invalidProteinPercentage = 40;
        int invalidFatPercentage = 40;
        int invalidCarbohydratePercentage = 30;

        // then
        assertThrows(RuntimeException.class, () -> {
            new DietPlanner(invalidProteinPercentage, invalidFatPercentage, invalidCarbohydratePercentage);
        });
    }
}