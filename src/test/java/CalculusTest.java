import calculus.Calculus;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;


class CalculusTest {

  @Test
  void arrayTest1() {

    String[] expected = {};

    Assertions.assertArrayEquals(expected, Calculus.getTokenizeForTests(""));
  }

  @Test
  void arrayTest2() {

    String[] expected = {"1"};

    Assertions.assertArrayEquals(expected, Calculus.getTokenizeForTests("1"));
  }

  @Test
  void arrayTest3() {

    String[] expected = {"1", "+", "x"};

    Assertions.assertArrayEquals(expected, Calculus.getTokenizeForTests("1+x"));
  }

  @Test
  void arrayTest4() {

    String[] expected = {"1", "+", "x"};

    Assertions.assertArrayEquals(expected, Calculus.getTokenizeForTests("1+ x"));
  }

  @Test
  void arrayTest5() {

    String[] expected = {"5", "*", "x", "+", "7", "-", "3"};

    Assertions.assertArrayEquals(expected, Calculus.getTokenizeForTests("5x + 7-3"));
  }

}
