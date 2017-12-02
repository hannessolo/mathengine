import calculus.Calculus;
import org.junit.jupiter.api.Test;
import calculus.Expression;

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

  @Test
  void arrayTest6() {

    String[] expected = {"(", "5", "*", "x", "+", "7", ")", "-", "3"};

    Assertions.assertArrayEquals(expected, Calculus.getTokenizeForTests("(5x + 7)-3"));
  }

  @Test
  void arrayTest7() {

    String[] expected = {"(", "5", ")", "*","3"};

    Assertions.assertArrayEquals(expected, Calculus.getTokenizeForTests("(5)3"));
  }

  @Test
  void arrayTest8() {

    String[] expected = {"5", "*", "(", "3", ")"};

    Assertions.assertArrayEquals(expected, Calculus.getTokenizeForTests("5(3)"));
  }

  @Test
  void evaluateTest1() {

    String expected = "(1+1)";

    Assertions.assertEquals(expected, Calculus.evaluate("1+1"));

  }

  @Test
  void evaluateTest2() {

    String expected = "((1+1)*4)";

    Assertions.assertEquals(expected, Calculus.evaluate("(1+1)*4"));

  }

  @Test
  void evaluateTest3() {

    String expected = "((1^(2^3))*5)";

    Assertions.assertEquals(expected, Calculus.evaluate("(1^2^3)5"));

  }

  @Test
  void evaluateTest4() {

    String expected = "(1^(2^3))";

    Assertions.assertEquals(expected, Calculus.evaluate("1^2^3"));

  }

}
