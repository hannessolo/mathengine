package calculus;

import java.util.ArrayList;
import java.util.Stack;

import static calculus.BinaryOperation.*;
import static calculus.UnaryOperation.*;

public class Calculus {

  public static String evaluate(String arg) {

    return null;

  }

  // take character, check if operator literal
  private static boolean isOperator(char c) {
    final char[] operators = {'+', '-', '*', '/'};
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

    char[] argsArray = args.toCharArray();

    // set 1 if processing number, set 2 if currently processing variable, 0 otherwise
    byte type = 0;

    // stores current number or variable, as processing happens character by character
    StringBuilder currentBuffer = new StringBuilder();

    // iterate through chars and place into
    for (int i = 0; i < argsArray.length; i++) {

      char c = argsArray[i];

      if (isOperator(c)) {
        if (currentBuffer.length() > 0) {
          // if there is a number in the buffer, place it into the output, clear buffer
          tokenList.add(currentBuffer.toString());
          currentBuffer.setLength(0);
        }
        // add operator to output
        type = 0;
        tokenList.add(Character.toString(c));
      } else if (c == '('){
        // check if bracket. Same as operator, but implied multiplication if no op present
        if (currentBuffer.length() > 0) {
          // if there is a number in the buffer, place it into the output, clear buffer
          // also means there is no operator, so implied multiplication
          tokenList.add(currentBuffer.toString());
          currentBuffer.setLength(0);
          tokenList.add("*");
        }
        // add operator to output
        type = 0;
        tokenList.add(Character.toString(c));
      } else if (c == ')') {
        if (currentBuffer.length() > 0) {
          // if there is a number in the buffer, place it into the output, clear buffer
          tokenList.add(currentBuffer.toString());
          currentBuffer.setLength(0);
        }
        // add operator to output
        type = 0;
        tokenList.add(Character.toString(c));
        //look ahead in case of implied multiplication
        for (int j = i + 1; j < argsArray.length; j++) {
          if (Character.isAlphabetic(argsArray[j]) || Character.isDigit(argsArray[j])) {
            tokenList.add("*");
            break;
          } else if (isOperator(argsArray[j])) {
            break;
          }
        }
      } else if (Character.isDigit(c)) {
        if (type == 2) {
          // if the type has changed, add to output
          tokenList.add(currentBuffer.toString());
          currentBuffer.setLength(0);
          tokenList.add("*");
        }
        // add char to buffer
        type = 1;
        currentBuffer.append(c);
      } else if (Character.isAlphabetic(c)) {
        if (type == 1) {
          // if type has changed, add to output
          tokenList.add(currentBuffer.toString());
          currentBuffer.setLength(0);
          tokenList.add("*");
        }
        // add char to buffer
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

