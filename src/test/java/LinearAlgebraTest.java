import linearalgebra.Solver;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

public class LinearAlgebraTest {

  @Test
  void solverTest1() {

    String expected = "x is 1.0, y is 1.0, z is 1.0, ";

    Assertions.assertEquals(expected, Solver.solve("x=1 and y=1 and z=1"));

  }

  @Test
  void solverTest2() {

    String expected = "x is 1.0, y is 1.0, z is 1.0, ";

    Assertions.assertEquals(expected, Solver.solve("2x+y=3 and y=1 and z=1"));

  }

  @Test
  void solverTest3() {

    String expected = "x is 2.0, y is 1.0, z is 3.0, ";

    Assertions.assertEquals(expected, Solver.solve("x=2 and x-2y=0 and z=3"));

  }

  @Test
  void solverTest4() {

    String expected = "x is 2.0, y is -1.0, z is 3.0, ";

    Assertions.assertEquals(expected, Solver.solve("x=2 and x+2y=0 and z=3"));

  }

}
