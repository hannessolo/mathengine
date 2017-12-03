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
  void arrayTest9() {

    String[] expected = {"neg", "5"};

    Assertions.assertArrayEquals(expected, Calculus.getTokenizeForTests("-5"));
  }

  @Test
  void arrayTest10() {

    String[] expected = {"neg", "5", "+", "neg", "7"};

    Assertions.assertArrayEquals(expected, Calculus.getTokenizeForTests("-5+-7"));
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

  @Test
  void evaluateTest5() {

    String expected = "((5*5)+(5*5))";

    Assertions.assertEquals(expected, Calculus.evaluate("5*5+5*5"));

  }

  @Test
  void evaluateTest6() {

    String expected = "(-5)";

    Assertions.assertEquals(expected, Calculus.evaluate("-5"));

  }

  @Test
  void evaluateTest7() {

    String expected = "((-5)+(-5))";

    Assertions.assertEquals(expected, Calculus.evaluate("-5+ -5"));

  }

  @Test
  void evaluateTest8() {

    String expected = "(-(-5))";

    Assertions.assertEquals(expected, Calculus.evaluate("--5"));

  }

  @Test
  void evaluateTest9() {

    String expected = "((-5)^3)";

    Assertions.assertEquals(expected, Calculus.evaluate("(-5)^3"));

  }

  @Test
  void differentiateTest1() {

    String expected = "((2*(x^(2-1.0)))*1.0)";

    Assertions.assertEquals(expected, Calculus.getDifferentiatedForTests("x^2"));

  }

  @Test
  void evaluateAtTest1() {

    double expected = 2;

    Assertions.assertEquals(expected, Calculus.evaluate("1+x", 1));

  }

}
