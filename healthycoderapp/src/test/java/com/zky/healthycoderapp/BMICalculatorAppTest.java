package com.zky.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BMICalculatorAppTest {

    private String environment = "dev";

    @BeforeAll
    static void setup() {
        System.out.println("Testing BMICalculatorApp");
    }

    @AfterAll
    static void done() {
        System.out.println("BMICalculatorAppTest done.");
    }

    @Nested
    class isDietRecommendedTests {

        @ParameterizedTest
        @ValueSource(doubles = { 89.0, 95.0, 110. })
        void should_ReturnTrue_When_dietRecommended(Double coderWeight) {
            // given
            double weight = coderWeight;
            double height = 1.72;

            // when
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            // then
            assertTrue(recommended);
        }

        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvSource(value = { "89.0, 1.72", "95.0, 1.75", "110.0, 1.78" })
        void should_ReturnTrue_When_dietRecommended2(Double coderWeight, Double coderHeight) {
            // given
            double weight = coderWeight;
            double height = coderHeight;

            // when
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            // then
            assertTrue(recommended);
        }

        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
        void should_ReturnTrue_When_dietRecommended3(Double coderWeight, Double coderHeight) {
            // given
            double weight = coderWeight;
            double height = coderHeight;

            // when
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            // then
            assertTrue(recommended);
        }
    }

    @Nested
    class FindCoderWithWorstBMITests {

        @Test
        @DisplayName(">>> sample method display name")
        @DisabledOnOs(OS.LINUX)
        void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {
            // given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 60.0));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));
            Coder expected = new Coder(1.82, 98.0);

            // when
            Coder coder = BMICalculator.findCoderWithWorstBMI(coders);

            // then
            assertAll(
                    () -> assertEquals(expected.getHeight(), coder.getHeight()),
                    () -> assertEquals(expected.getWeight(), coder.getWeight()));
        }

        @Test
        void should_ReturnCoderWithWorstBMIIn10Ms_When_CoderListHas10000Elements() {
            // given
            assumeTrue(BMICalculatorAppTest.this.environment.equals("prod"));

            List<Coder> coders = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                coders.add(new Coder(1.0 + i / 10000.0, 10.0 + i));
            }

            // when
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);

            // then
            assertTimeout(Duration.ofMillis(10), executable);
        }

        @Test
        @Disabled
        void should_ReturnNullWorstBMICoder_When_CoderListEmpty() {
            // given
            List<Coder> coders = new ArrayList<>();

            // when
            Coder coder = BMICalculator.findCoderWithWorstBMI(coders);

            // then
            assertNull(coder);
        }
    }

    @Nested
    class GetBMIScoresTests {

        @Test
        void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty() {
            // given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 60.0));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));
            double[] expected = { 18.52, 29.59, 19.53 };

            // when
            double[] bmiScores = BMICalculator.getBMIScores(coders);

            // then
            assertArrayEquals(expected, bmiScores);
        }
    }
}
