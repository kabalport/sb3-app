package com.kabalport.sb3app.calculator;

import com.kabalport.sb3app.calcaulator.MyCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyCalculatorTest {

  @Test
  @DisplayName("MyCalculator 더하기 테스트")
  void addTest() {
    // AAA 패턴

    // Arrange - 준비
    MyCalculator myCalculator = new MyCalculator();
    // Act - 행동
    myCalculator.add(10.0);
    // Assert - 단언/검증
    Assertions.assertEquals(10.0, myCalculator.getResult());
  }

  @Test
  @DisplayName("MyCalculator 빼기 테스트")
  void minusTest() {
    // GWT 패턴

    // Given - 준비 - Arrange
    MyCalculator myCalculator = new MyCalculator(10.0);

    // When - 행동/연산 - Act
    myCalculator.minus(5.0);

    // Then - 검증/비교/단언 - Assert
    Assertions.assertEquals(5.0, myCalculator.getResult());
  }

  @Test
  @DisplayName("MyCalculator 곱하기 테스트")
  void multiplyTest() {
    // given
    MyCalculator myCalculator = new MyCalculator(2.0);
    // when
    myCalculator.multiply(2.0);
    // then
    Assertions.assertEquals(4.0, myCalculator.getResult());
  }

  @Test
  @DisplayName("MyCalculator 나누기 테스트")
  void divideTest() {
    // given
    MyCalculator myCalculator = new MyCalculator(10.0);
    // when
    myCalculator.divide(2.0);
    // then
    Assertions.assertEquals(5.0, myCalculator.getResult());
  }

  @Test
  @DisplayName("MyCalculator 사칙연산 테스트")
  void complicatedCalculateTest() {
    // given
    MyCalculator myCalculator = new MyCalculator();

    // when
    Double result = myCalculator.add(10.0).minus(4.0).multiply(2.0).divide(3.0).getResult();
    // then
    Assertions.assertEquals(4.0, result);
  }

  @Test
  @DisplayName("MyCalculator 0으로 나눴을 때에는 ZeroDivisionException을 발생시켜야 합니다.")
  void divideZeroTest() {
    // given
    MyCalculator myCalculator = new MyCalculator(10.0);

    // when & then
    Assertions.assertThrows(
        MyCalculator.ZeroDivisionException.class,
        () -> {
          myCalculator.divide(0.0);
        });
  }
}
