package linearalgebra;

import calculus.Calculus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Solver {

  public static String solve(String arg) {

    final double acceptableTolerance = Math.pow(10, -5);

    String[] equations = arg.split("and");

    ArrayList<String> variableLookup = new ArrayList<>();

    double[][] A = new double[equations.length][equations.length];
    double[]   b = new double[equations.length];

    for (int i = 0; i < equations.length; i++) {

      String[] tokenizedEquation = calculus.Calculus.tokenize(equations[i]);

      for (int j = 0; j < tokenizedEquation.length; j++) {

        if (Pattern.matches("^[a-zA-Z]*$", tokenizedEquation[j])) {

          if (!variableLookup.contains(tokenizedEquation[j])) {

            variableLookup.add(tokenizedEquation[j]);

          }

        }

      }

      int lastVarIndex = -1;

      for (int j = 0; j < tokenizedEquation.length; j++ ) {

        if (variableLookup.contains(tokenizedEquation[j])) {

          if (lastVarIndex + 2 == j || j == 0) {

            boolean negativeSign = tokenizedEquation[lastVarIndex + 1].equals("-");

            A[i][variableLookup.indexOf(tokenizedEquation[j])] = negativeSign ? -1 : 1;

          } else {

            A[i][variableLookup.indexOf(tokenizedEquation[j])] = calculus.Calculus.evaluate(
                String.join("", Arrays.copyOfRange(tokenizedEquation,
                    lastVarIndex + 1, j - 1)), 0);
          }

          lastVarIndex = j;

        } else if (tokenizedEquation[j].equals("=")) {

          b[i] = calculus.Calculus.evaluate(String.join("", Arrays.copyOfRange
              (tokenizedEquation,j + 1, tokenizedEquation.length)), 0);

        }

      }

    }

    GaussianEliminationSquareMatrices solver
        = new GaussianEliminationSquareMatrices(acceptableTolerance);

    final double[] solution = solver.solve(A, b);

    StringBuilder sol = new StringBuilder();
    for (int i = 0; i < solution.length; i++) {

      sol.append(variableLookup.get(i));
      sol.append(" is ");
      sol.append(solution[i]);
      sol.append(", ");

    }

    return sol.toString();

  }

}
