package calculus;

import java.util.Stack;

import static calculus.BinaryOperation.*;
import static calculus.UnaryOperation.*;

public class Calculus {

    public static String evaluate(String arg) {

        return postfixEvaluator(postfixConverter(arg)).differentiate("x").getPrintable();

    }

    private static Expression postfixEvaluator(String ex) {

        Stack<Expression> stack = new Stack<>();
        String num = "";
        String func = "";

        for (char c : ex.toCharArray()) {

            if (Character.isDigit(c)) {

                num = num.concat(Character.toString(c));

            } else if (isUnOperator(func)) {
                // yes this is also hardcoded and broken. fix tomorrow.

                stack.push(new UnaryApplication(NEGATE, stack.pop()));
                func = "";

            } else if (Character.isWhitespace(c) && (!"".equals(num)) ) {

                stack.push(new Identity(Double.parseDouble(num)));
                num = "";

            } else if (Character.isWhitespace(c) && (!"".equals(func))) {

                stack.push(new Identity(func));
                func = "";

            } else if (Character.isAlphabetic(c)) {

                func = func.concat(Character.toString(c));

            } else if (isOperator(c)) {

                if (!"".equals(num)) {
                    stack.push(new Identity(Double.parseDouble(num)));
                    num = "";
                }

                if (!"".equals(func)) {
                    stack.push(new Identity(func));
                    func = "";
                }

                Expression arg1;

                switch (c) {
                    case ('+'):
                        arg1 = stack.pop();
                        stack.push(new BinaryApplication(ADD, stack.pop(), arg1));
                        break;
                    case ('-'):
                        arg1 = stack.pop();
                        stack.push(new BinaryApplication(SUBTRACT, stack.pop(), arg1));
                        break;
                    case ('*'):
                        arg1 = stack.pop();
                        stack.push(new BinaryApplication(MULTIPLY, stack.pop(), arg1));
                        break;
                    case ('/'):
                        arg1 = stack.pop();
                        stack.push(new BinaryApplication(DIVIDE, stack.pop(), arg1));
                        break;
                }
            }
        }

        return stack.pop();

    }

    private static boolean isOperator(char c) {
        char[] operators = {'+', '-', '/', '*'};
        for (char o : operators) {
            if (o == c) return true;
        }
        return false;
    }

    private static boolean isUnOperator(String func) {
        String[] operators = {"neg"};
        for (String o : operators) {
            if (o.equals(func)) return true;
        }
        return false;
    }

    private static String postfixConverter(String ex) {

        // todo: fix: make it also do the right thing if there is no tailing whitespace

        Stack<Character> stack = new Stack<>();
        String postfix = "";
        String currentStream = "";

        for (char c : ex.toCharArray()) {

            if (Character.isAlphabetic(c) || Character.isDigit(c)) {
//              TODO: Make this also happen on closing and opening brackets. So neg(5) and neg ( 5 ) are equivalent.
                currentStream = currentStream.concat(Character.toString(c));

            } else if (Character.isWhitespace(c)) {

                if (isUnOperator(currentStream)) {

//                  TODO! THIS IS HARDCODED IN AS NEGATIVE! MAKE IT WORK FOR OTHER OPERATIONS!
                    stack.push('-');

                } else {
                    postfix = postfix.concat(currentStream);
                    postfix = postfix.concat(" ");
                }

                currentStream = "";


            } else if (c == '(') {

                stack.push('(');

            } else if (c == ')') {

                while (stack.peek() != '(') {
                    postfix = postfix.concat(Character.toString(stack.pop()));
                }

                stack.pop();

//              TODO! THIS IS ALSO HARDCODED! MAKE LOOKUP LIST INSTEAD WHEN LESS TIRED!
                if (stack.peek() == '-') {
                    postfix = postfix.concat("neg");
                    stack.pop();
                }

            } else if (isOperator(c)) {

                if (stack.empty()) {

                    stack.push(c);

                } else {

                    while (!(stack.empty() || stack.peek() == '(')) {
                        if (hasHiOrEqPrecedence(stack.peek(), c)) {
                            postfix = postfix.concat(Character.toString(stack.pop()));
                        } else {
                            stack.push(c);
                            break;
                        }
                    }

                    if (stack.isEmpty() || stack.peek() == '(') {
                        stack.push(c);
                    }
                }
            }
        }

        while (!stack.empty()) {
            postfix = postfix.concat(Character.toString(stack.pop()));
        }

        return postfix;

    }

    private static boolean hasHiOrEqPrecedence(char a, char b) {
        return !((a == '+' || a == '-') && (b == '*' || b == '/'));
    }

}

