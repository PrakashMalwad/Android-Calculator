package com.mld.customcalculator;
import java.util.Stack;
class CalculationHelper {
    public static String evaluateExpression(String expression) {
        try {
            expression = preprocess(expression);
            Stack<Double> operands = new Stack<>();
            Stack<Character> operators = new Stack<>();

            for (int i = 0; i < expression.length(); i++) {
                char currentChar = expression.charAt(i);
                if (Character.isDigit(currentChar) || currentChar == '.') {
                    StringBuilder number = new StringBuilder();
                    while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                        number.append(expression.charAt(i));
                        i++;
                    }
                    i--;
                    operands.push(Double.parseDouble(number.toString()));
                } else if (currentChar == '(') {
                    operators.push(currentChar);
                } else if (currentChar == ')') {
                    while (operators.peek() != '(') {
                        evaluateOperation(operands, operators);
                    }
                    operators.pop(); // Remove the '('
                } else if (isOperator(currentChar)) {
                    while (!operators.isEmpty() && hasPrecedence(currentChar, operators.peek())) {
                        evaluateOperation(operands, operators);
                    }
                    operators.push(currentChar);
                }
            }

            while (!operators.isEmpty()) {
                evaluateOperation(operands, operators);
            }

            // Returning the result as a string
            return String.valueOf(operands.pop());
        } catch (Exception e) {
            e.printStackTrace();
            return "Error evaluating expression";
        }
    }

    private static String preprocess(String expression) {
        return expression.replaceAll("\\s+", ""); // Removing white spaces
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        return (op1 == '*' || op1 == '/') || (op2 == '+' || op2 == '-');
    }

    private static void evaluateOperation(Stack<Double> operands, Stack<Character> operators) {
        double b = operands.pop();
        double a = operands.pop();
        char operator = operators.pop();
        double result = performOperation(a, b, operator);
        operands.push(result);
    }

    private static double performOperation(double a, double b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    private static double performOperation(double a, char operator) {
        switch (operator) {
            case '-':
                return -a; // Unary minus
            default:
                throw new IllegalArgumentException("Invalid unary operator");
        }
    }
}