package com.zky.realestateapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ApartmentRaterTest {

    @ParameterizedTest(name = "area: {0}, price: {1}, rating: {2}")
    @CsvSource({
        "30, 3000.0, 0",
        "30, 120000.0, 1",
        "30, 240000.0, 2"
    })
    void should_ReturnCorrectRating_When_CorrectApartment(double area, double price, int expected) {
        // Arrange
        Apartment apartment = new Apartment(area, BigDecimal.valueOf(price));

        // Act
        int rate = ApartmentRater.rateApartment(apartment);

        // Assert
        assertEquals(expected, rate);
    }

    @Test
    void should_ReturnErrorValue_When_IncorrectApartment() {
        // Arrange
        Apartment apartment = new Apartment(0, BigDecimal.valueOf(10));

        // Act
        int rate = ApartmentRater.rateApartment(apartment);

        // Assert
        assertEquals(-1, rate);
    }

    @Test
    void should_CalculateAverageRating_When_CorrectApartmentList() {
        // Arrange
        Apartment apartment1 = new Apartment(30, BigDecimal.valueOf(3000.0));
        Apartment apartment2 = new Apartment(30, BigDecimal.valueOf(120000.0));
        Apartment apartment3 = new Apartment(30, BigDecimal.valueOf(240000.0));

        // Act
        double averageRating = ApartmentRater.calculateAverageRating(Arrays.asList(apartment1, apartment2, apartment3));

        // Assert
        assertEquals(1.0, averageRating);
    }

    @Test
    void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {

        Executable executable = () -> ApartmentRater.calculateAverageRating(Arrays.asList());

        assertThrows(RuntimeException.class, executable);
    }
}
