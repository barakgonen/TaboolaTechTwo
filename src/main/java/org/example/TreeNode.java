package org.example;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static org.example.Constants.SUPPORTED_OPERATORS;

public class TreeNode {
    TreeNode leftOperand;
    TreeNode rightOperand;
    Number value;
    Optional<BiFunction<Number, Number, Number>> function;


    public TreeNode(String expression) {
        initializeFromString(expression);
    }

    private boolean isNumber(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isSograim(String expression) {
        return expression.contains("(") && expression.contains(")");
    }

    private void initializeFromString(String expression) {
        if (expression.startsWith("(") && expression.endsWith(")")) {
            initializeFromString(expression.substring(1, expression.length() - 1));
        } else if (expression.contains(" ")) {
            // THIS IS NOT THE MOST SIMPLIFIED EXPRESSION, LETS SIMPLIFY IT
            String[] splitExpression = expression.split(" ");

            if (isSograim(expression)) {
                int sograimBegin = expression.lastIndexOf("(");
                int sograimEnd = expression.indexOf(")");
                if (sograimBegin > 0) {
                    // means it's somewhere in the expression
                    // need to parse left + operator between index 0 to sograimBegin
                    int operatorIndex = 0;
                    for (int i = 0; i < sograimBegin && operatorIndex == 0; i++) {
                        if (SUPPORTED_OPERATORS.containsKey(splitExpression[i])) {
                            operatorIndex = i;
                            function = Optional.of(SUPPORTED_OPERATORS.get(splitExpression[i]));
                        }
                    }

                    leftOperand = new TreeNode(Arrays.stream(splitExpression).limit(operatorIndex).collect(Collectors.joining(" ")));
                    rightOperand = new TreeNode(Arrays.stream(splitExpression).skip(operatorIndex + 1).collect(Collectors.joining(" ")));
                } else {
                    leftOperand = new TreeNode(expression.substring(0, sograimEnd  +1));
                    int leftExpression = sograimEnd + 1;
                    // need to find the function and parse it
                    for (int i = sograimEnd; i < expression.length(); i++) {
                        leftExpression++;
                        if (!Character.isDigit(expression.charAt(i)) && SUPPORTED_OPERATORS.containsKey((String.valueOf(expression.charAt(i))))) {
                            function = Optional.of(SUPPORTED_OPERATORS.get((String.valueOf(expression.charAt(i)))));
                            break;
                        }

                    }

                    rightOperand = new TreeNode(expression.substring(leftExpression));
                }
//                if (sograimEnd == expression.length()) {
//                    System.out.println("it ends in the end of the string");
//                    leftOperand = new TreeNode(Arrays.stream(splitExpression).limit(operatorIndex).collect(Collectors.joining(" ")));
//                    rightOperand = new TreeNode(Arrays.stream(splitExpression).skip(operatorIndex + 1).collect(Collectors.joining(" ")));
            } else {
                // without sograim, parsing in the intuitive order
                // finding index of operator
                int operatorIndex = -1;
                for (int i = 0; i < splitExpression.length && operatorIndex == -1; i++) {
                    if (SUPPORTED_OPERATORS.containsKey(splitExpression[i])) {
                        operatorIndex = i;
                        function = Optional.of(SUPPORTED_OPERATORS.get(splitExpression[i]));
                    }
                }

                leftOperand = new TreeNode(Arrays.stream(splitExpression).limit(operatorIndex).collect(Collectors.joining(" ")));
                rightOperand = new TreeNode(Arrays.stream(splitExpression).skip(operatorIndex + 1).collect(Collectors.joining(" ")));

            }
        } else {
            if (isNumber(expression)) {
                // THIS IS A NUMBER IN THE MOST SIMPLIFIED SOLUTION
                value = Integer.valueOf(expression);
            } else if (SUPPORTED_OPERATORS.containsKey(expression)) {
                function = Optional.of(SUPPORTED_OPERATORS.get(expression));
            } else {
                System.out.println("BG WE HAVE A PROBLEM");
            }
        }
    }
}
