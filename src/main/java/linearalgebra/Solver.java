package linearalgebra;

public class Solver {

  public static String solve(String arg) {

    final double acceptableTolerance = Math.pow(10, -5);

    String[] equations = arg.split("and");

    EquationParser parser = new EquationParser(equations, equations.length);

    double[][] A = parser.getToMatrix();
    double[]   b = parser.getToVector();

    GaussianEliminationSquareMatrices solver
        = new GaussianEliminationSquareMatrices(acceptableTolerance);

    final double[] solution = solver.solve(A, b);

    StringBuilder sol = new StringBuilder();
    for (int i = 0; i < solution.length; i++) {

      sol.append(parser.getVariables().get(i));
      sol.append(" is ");
      sol.append(solution[i]);
      sol.append(", ");

    }

    return sol.toString();

  }

}
