package calculus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static calculus.BinaryOperation.*;
import static calculus.UnaryOperation.*;

public class Calculus {

  public static String evaluate(String arg) {

    String result;

    try {
      result = evaluate(tokenize(arg)).getPrintable();
    } catch (NullPointerException e) {
      return "An exception occurred.";
    }

    return result;

  }

  public static String[] getTokenizeForTests(String args) {

    return tokenize(args);

  }

  // take character, check if operator literal
  private static boolean isOperator(String s) {
    final String[] operators = {"+", "-", "*", "/", "^", "neg"};
    for (String o : operators) {
      if (o.equals(s)) {
        return true;
      }
    }
    return false;
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

      if (isOperator(Character.toString(c))) {
        try {
          if (c == '-' && isOperator(tokenList.get(i - 1))) {
            // The minus is a negation as opposed to an operator
            // implies buffer is empty
            tokenList.add("neg");
            type = 0;
            continue;
          }
        } catch (IndexOutOfBoundsException e) {
          if (i == 0) {
            // in case it is in the first index
            tokenList.add("neg");
            type = 0;
            continue;
          }
        }
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
        // add bracket to output
        type = 0;
        tokenList.add(Character.toString(c));
      } else if (c == ')') {
        if (currentBuffer.length() > 0) {
          // if there is a number in the buffer, place it into the output, clear buffer
          tokenList.add(currentBuffer.toString());
          currentBuffer.setLength(0);
        }
        // add bracket to output
        type = 0;
        tokenList.add(Character.toString(c));
        //look ahead in case of implied multiplication
        for (int j = i + 1; j < argsArray.length; j++) {
          if (Character.isAlphabetic(argsArray[j]) || Character.isDigit(argsArray[j])) {
            tokenList.add("*");
            break;
          } else if (isOperator(Character.toString(argsArray[j]))) {
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

    // add remaining buffer to output
    if (currentBuffer.length() > 0) {
      tokenList.add(currentBuffer.toString());
    }

    return tokenList.toArray(new String[tokenList.size()]);

  }

  private static Expression evaluate(String[] args) {

    final HashMap<String, Integer> operatorPrecedence = new HashMap<String, Integer>()
    {{
      put("$", 0);
      put("(", 1);
      put(")", 1);
      put("+", 6);
      put("-", 6);
      put("neg", 6);
      put("*", 7);
      put("/", 7);
      put("^", 8);
    }};

    final HashMap<String, Boolean> isRightAssociative = new HashMap<String, Boolean>()
    {{
      put("$", false);
      put("(", false);
      put(")", false);
      put("+", false);
      put("-", false);
      put("*", false);
      put("/", false);
      put("neg", true);
      put("^", true);
    }};

    final HashMap<String, Boolean> isUnOp = new HashMap<String, Boolean>()
    {{
      put("$", false);
      put("(", false);
      put(")", false);
      put("+", false);
      put("-", false);
      put("*", false);
      put("/", false);
      put("neg", true);
      put("^", false);
    }};

    Stack<Expression> expressionStack = new Stack<>();
    Stack<String> operatorStack = new Stack<>();

    operatorStack.push("$");

    for (String s : args) {

      if (s.equals("(")) {

        operatorStack.push("(");

      } else if (s.equals(")")) {

        do {

          Expression arg2 = expressionStack.pop();
          Expression arg1 = expressionStack.pop();

          expressionStack.push(buildExpression(operatorStack.pop(), arg1, arg2));

        } while (!operatorStack.peek().equals("("));

        operatorStack.pop();

      } else if (operatorPrecedence.get(s) != null) {
        // is operator

        // check if either precedence is greater, or equal and right associative
        if (operatorPrecedence.get(s) > operatorPrecedence.get(operatorStack.peek()) ||
            (operatorPrecedence.get(s).equals(operatorPrecedence.get(operatorStack.peek())) &&
                isRightAssociative.get(s))) {

          operatorStack.push(s);

        } else {

          while (!(operatorPrecedence.get(s) > operatorPrecedence.get(operatorStack.peek()))) {

            if (isUnOp.get(operatorStack.peek())) {

              Expression arg = expressionStack.pop();

              expressionStack.push(buildExpression(operatorStack.pop(), arg, null));

            } else {

              Expression arg2 = expressionStack.pop();
              Expression arg1 = expressionStack.pop();

              expressionStack.push(buildExpression(operatorStack.pop(), arg1, arg2));

            }

          }

          operatorStack.push(s);

        }

      } else {
        // is variable or number
        expressionStack.push(new Identity(s));
      }

    }

    // now empty operation stack
    while (operatorStack.size() > 1) {

      if (isUnOp.get(operatorStack.peek())) {

        Expression arg = expressionStack.pop();

        expressionStack.push(buildExpression(operatorStack.pop(), arg, null));

      } else {

        Expression arg2 = expressionStack.pop();
        Expression arg1 = expressionStack.pop();

        expressionStack.push(buildExpression(operatorStack.pop(), arg1, arg2));

      }

    }

    return expressionStack.pop();

  }

  private static Expression buildExpression(String op, Expression arg1, Expression arg2) {

    switch(op) {
      case "+":
        return new BinaryApplication(ADD, arg1, arg2);
      case "-":
        return new BinaryApplication(SUBTRACT, arg1, arg2);
      case "*":
        return new BinaryApplication(MULTIPLY, arg1, arg2);
      case "/":
        return new BinaryApplication(DIVIDE, arg1, arg2);
      case "^":
        return new BinaryApplication(POWER, arg1, arg2);
      case "neg":
        return new UnaryApplication(NEGATE, arg1);
      default:
        return null;
    }

  }

}

