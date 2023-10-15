package com.kabalport.sb3app;

import java.util.List;
import org.junit.jupiter.api.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JUnitPracticeTest {

  @Test
  @DisplayName("Assert Equals 메소드 테스트")
  public void assert_Equals_Test() {
    String expect = "Something";
    String actual = "Something";

    Assertions.assertEquals(expect, actual);
  }

  @Test
  @DisplayName("Assert Equals Not 메소드 테스트")
  public void assertEqualsNotTest() {
    String expect = "Something";
    String actual = "Hello";

    Assertions.assertNotEquals(expect, actual);
  }

  @Test
  @DisplayName("Assert True 메소드 테스트")
  public void assertTrueTest() {
    Integer a = 10;
    Integer b = 10;

    Assertions.assertTrue(a.equals(b));
  }

  @Test
  @DisplayName("Assert False 메소드 테스트")
  public void assertFalseTest() {
    Integer a = 1;
    Integer b = 2;
    Assertions.assertFalse(a.equals(b));
  }

  @Test
  @DisplayName("Assert Throws 메소드 테스트")
  public void assertThrowsTest() {
    Assertions.assertThrows(
        RuntimeException.class,
        () -> {
          throw new RuntimeException("임의로 발생시킨 에러");
        });
  }

  @Test
  @DisplayName("Assert NotNull 메소드 테스트")
  public void assertNotNullTest() {
    String value = "Hello";
    Assertions.assertNotNull(value);
  }

  @Test
  @DisplayName("Assert Null 메소드 테스트")
  public void assertNullTest() {
    String value = null;
    Assertions.assertNull(value);
  }

  @Test
  @DisplayName("Assert IterableEquals 메소드 테스트")
  public void assertIterableEqualsTest() {
    List<Integer> list1 = List.of(1, 2);
    List<Integer> list2 = List.of(1, 2);

    Assertions.assertIterableEquals(list1, list2);
  }

  @Test
  @DisplayName("Assert All 메소드 테스트")
  public void assertAllTest() {
    String expect = "Something";
    String actual = "Something";

    List<Integer> list1 = List.of(1, 2);
    List<Integer> list2 = List.of(1, 2);

    Assertions.assertAll(
        "Assert All",
        List.of(
            () -> {
              Assertions.assertEquals(expect, actual);
            },
            () -> {
              Assertions.assertIterableEquals(list1, list2);
            }));
  }
}
