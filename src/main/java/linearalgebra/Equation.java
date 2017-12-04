package linearalgebra;

import java.util.Arrays;

public class Equation {

  private String[] vars;
  private String[] eqParts;

  private String[] tokenizedString;

  private double[] coeffs;
  private double result;

  Equation(String eq, String[] vars) {
    tokenizedString = calculus.Calculus.tokenize(eq);
    this.vars = vars;
    coeffs = new double[vars.length];
    this.splitEq();
    this.determineCoeffs();
  }

  private int findVariablePosition(String var) {

    for (int i = 0; i < vars.length; i++) {

      if (vars[i].equals(var)) {
        return i;
      }

    }

    return -1;

  }

  private void splitEq() {

    int previousIndexToRemember = 0;

    eqParts = new String[vars.length + 1];

    for (int i = 0; i < tokenizedString.length; i++) {

      if (tokenizedString[i].equals("+") || tokenizedString[i].equals("=")) {

        String eqPart;

        try {
          // multiplication operator should be at i - 2
          eqPart = String
              .join("", Arrays.copyOfRange(tokenizedString, previousIndexToRemember, i - 2));
        } catch (IllegalArgumentException e) {
          eqPart = String
              .join("", Arrays.copyOfRange(tokenizedString, previousIndexToRemember, i - 1));
        }

        if (eqPart.equals("")) {
          eqPart = "1";
        } else if (eqPart.equals("-")) {
          eqPart = "-1";
        }

        eqParts[findVariablePosition(tokenizedString[i - 1])] = eqPart;
        previousIndexToRemember = i + 1;

      } else if (tokenizedString[i].equals("-")) {

        String eqPart;

        try {
          // multiplication operator should be at i - 2
          eqPart = String
              .join("", Arrays.copyOfRange(tokenizedString, previousIndexToRemember, i - 2));
        } catch (IllegalArgumentException e) {
          eqPart = String
              .join("", Arrays.copyOfRange(tokenizedString, previousIndexToRemember, i - 1));
        }

        if (eqPart.equals("")) {
          eqPart = "1";
        } else if (eqPart.equals("-")) {
          eqPart = "-1";
        }

        eqParts[findVariablePosition(tokenizedString[i - 1])] = eqPart;
        previousIndexToRemember = i;

      }

    }

    // multiplication operator should be at i - 2
    String eqPart = String.join("", Arrays.copyOfRange(tokenizedString,  previousIndexToRemember, vars.length + 1));

    if (eqPart.equals("")) {
      eqPart = "1";
    } else if (eqPart.equals("-")) {
      eqPart = "-1";
    }

    eqParts[vars.length] = eqPart;

  }

  private void determineCoeffs() {

    for (int i = 0; i < eqParts.length - 1; i++) {

      if (eqParts[i] == null) {
        coeffs[i] = 0;
      }
      coeffs[i] = calculus.Calculus.evaluate(eqParts[i], 0);

    }

    if (eqParts[eqParts.length - 1] == null) {
      result = 0;
    } else {
      result = calculus.Calculus.evaluate(eqParts[eqParts.length - 1], 0);
    }

  }

  public double getResult() {
    return result;
  }

  public double[] getCoeffs() {
    return coeffs;
  }
}
