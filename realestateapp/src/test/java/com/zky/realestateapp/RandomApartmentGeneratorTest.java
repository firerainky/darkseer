package com.zky.realestateapp;

import java.math.BigDecimal;

import org.junit.jupiter.api.RepeatedTest;

public class RandomApartmentGeneratorTest {

    @RepeatedTest(10)
    void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice() {
        // Arrange
        RandomApartmentGenerator generator = new RandomApartmentGenerator();

        // Act
        Apartment apartment = generator.generate();

        // Assert
        assert apartment.getArea() > 0;
        assert apartment.getPrice().doubleValue() > 0;
    }

    @RepeatedTest(10)
    void should_GenerateCorrectApartment_When_CustomMinAreaMinPrice() {
        // Arrange
        RandomApartmentGenerator generator = new RandomApartmentGenerator(10.0, BigDecimal.valueOf(200.0));

        // Act
        Apartment apartment = generator.generate();

        // Assert
        assert apartment.getArea() > 0;
        assert apartment.getPrice().doubleValue() > 0;
    }
}
