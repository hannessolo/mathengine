package linearalgebra;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class EquationParser {

  private int numberOfEquations;
  private String[] stringEquations;
  private ArrayList<String> variables;
  private Equation[] equations;

  EquationParser(String[] eqs, int n) {

    numberOfEquations = n;
    stringEquations = eqs;

    variables = new ArrayList<>();
    determineVariables();

    equations = new Equation[numberOfEquations];

    for (int i = 0; i < equations.length; i++) {
      equations[i] = new Equation(stringEquations[i], variables.toArray(new String[numberOfEquations]));
    }

  }

  private void determineVariables() {

    for (int i = 0; i < stringEquations.length; i++) {

      String[] tokenizedEquation = calculus.Calculus.tokenize(stringEquations[i]);

      for (int j = 0; j < tokenizedEquation.length; j++) {

        if (Pattern.matches("^[a-zA-Z]*$", tokenizedEquation[j])) {

          if (!variables.contains(tokenizedEquation[j])) {

            variables.add(tokenizedEquation[j]);

          }

        }
      }
    }
  }

  public double[] getToVector() {

    double[] result = new double[numberOfEquations];

    for (int i = 0; i < numberOfEquations; i++) {

      result[i] = equations[i].getResult();

    }

    return result;

  }

  public double[][] getToMatrix() {

    double[][] result = new double[numberOfEquations][numberOfEquations];

    for (int i = 0; i < numberOfEquations; i++) {

      result[i] = equations[i].getCoeffs();

    }

    return result;

  }

  public ArrayList<String> getVariables() {
    return variables;
  }

}
