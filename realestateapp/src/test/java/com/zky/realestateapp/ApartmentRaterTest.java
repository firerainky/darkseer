package com.zky.realestateapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class ApartmentRaterTest {

    @Test
    void should_ReturnZero_When_ApartmentIsAtBestPriceAboveAreaRatio() {
        // Arrange
        Apartment apartment = new Apartment(30, BigDecimal.valueOf(3000.0));

        // Act
        int rate = ApartmentRater.rateApartment(apartment);

        // Assert
        assertEquals(0, rate);
    }
}
