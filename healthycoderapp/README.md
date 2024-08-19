# Healthy Coder App with testing in JUnit5

From a Java Unit testing course on Udemy

<https://www.udemy.com/course/junit5-for-beginners/>

## Testing tips

- Test a method repeated with `@RepeatedTest`
- Give a series of test params before test implementations (`@ParameterizedTest`, `@ValueSources`, `@CsvSources` and others)
- Assert throws (Executable with lambda expression)
- `@assertAll` for asserting multiple goals
- Performance test (`@assertTimeout` with Executable)
- Use `@assumeTrue` or `@Disabled` to skip tests not fulfilled.

## TDD for cardio calculator

### Business Requirement

Rate user's activity level (bad - average - good). Input:

(1) The weekly time [min] spent doing cardo;

(2) The number of workout sessions (1 workout = 45 minutes).

Throw exception when any input below 0.

Calculate the weeekly total, divide by 7 to find the daily average.

If < 20, return "bad".

If >= 20 and < 40, return "average".

If >= 40, return "good".
