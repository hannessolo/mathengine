package calculus;

import java.util.ArrayList;
import java.util.Stack;

import static calculus.BinaryOperation.*;
import static calculus.UnaryOperation.*;

public class Calculus {

  public static String evaluate(String arg) {

    return null;

  }

  // take character, check if operator literal or bracket
  private static boolean isOperator(char c) {
    final char[] operators = {'+', '-', '*', '/', '(', ')'};
    for (char o : operators) {
      if (o == c) {
        return true;
      }
    }
    return false;
  }

  public static String[] getTokenizeForTests(String args) {

    return tokenize(args);

  }

  private static String[] tokenize(String args) {

    ArrayList<String> tokenList = new ArrayList<>();

    // set 1 if processing number, set 2 if currently processing variable, 0 otherwise
    byte type = 0;

    // stores current number or variable, as processing happens character by character
    StringBuilder currentBuffer = new StringBuilder();

    // iterate through chars and place into
    for (char c : args.toCharArray()) {

      if (isOperator(c)) {
        if (currentBuffer.length() > 0) {
          // if there is a number in the buffer, place it into the output, clear buffer
          tokenList.add(currentBuffer.toString());
          currentBuffer.setLength(0);
        }
        // add operator to output
        type = 0;
        tokenList.add(Character.toString(c));
      } else if (Character.isDigit(c)) {
        if (type == 2) {
          tokenList.add(currentBuffer.toString());
          currentBuffer.setLength(0);
          tokenList.add("*");
        }
        type = 1;
        currentBuffer.append(c);
      } else if (Character.isAlphabetic(c)) {
        if (type == 1) {
          tokenList.add(currentBuffer.toString());
          currentBuffer.setLength(0);
          tokenList.add("*");
        }
        type = 2;
        currentBuffer.append(c);
      }

    }

    if (currentBuffer.length() > 0) {
      tokenList.add(currentBuffer.toString());
    }

    return tokenList.toArray(new String[tokenList.size()]);

  }

  private static Expression evaluate() {

    return null;

  }

}

