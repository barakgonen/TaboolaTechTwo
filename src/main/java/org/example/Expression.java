package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.Constants.SUPPORTED_OPERATORS;

/**
 * Represents arithmetic expression.
 * This class gets the expression to solve, and mapping of variables and tries to solve the equation
 */
public class Expression {
    private static final Logger LOGGER = LogManager.getLogger();
    Map<String, Long> variableToResult;
    String expressionToSolve;

    /**
     * Constructor for multi equation system solution
     * @param variableToResult - mapping of variables to their value, for example i = 4
     * @param expressionToSolve - the expression to solve
     */
    public Expression(Map<String, Long> variableToResult, String expressionToSolve) {
        this.variableToResult = variableToResult;
        this.expressionToSolve = expressionToSolve;
    }

    /**
     * Constructor for simple equation, without external dependencies, "self-contained" expression
     * @param expressionToSolve - the expression to solve
     */
    public Expression(String expressionToSolve) {
        this(null, expressionToSolve);
    }

    public Long solve() throws InvalidSyntaxException {
        /**
         * 1 calculate all ()
         * 2 calculate all * OR /
         * 3 calculate all + OR -
         *
         */

        String modifiedExpression = modifyInputString();

        Stack<String> input = new Stack<>();
        Stack<String> temp = new Stack<>();

        LinkedHashMap<String, Long> tokensCounter = getMappings(modifiedExpression);

        if (containsParenthesis(tokensCounter)) {
            if (!tokensCounter.get("(").equals(tokensCounter.get(")"))) {
                throw new InvalidSyntaxException("The following expression: " + expressionToSolve + ", is invalid expression, because number of ( is: " + tokensCounter.get("\"") + ", close: " + tokensCounter.get(")\\"));
            }
        }
        input.addAll(Arrays.asList(modifiedExpression.split(" ")));

        while (input.contains("(") && input.contains(")")) {
            // PREPARE TMP
            while (!input.isEmpty()) {
                temp.add(input.pop());
            }

            boolean gotRightOpeningParenthesis = false;
            // HANDLE PARENTHESIS
            while (!temp.isEmpty() && !gotRightOpeningParenthesis) {
                var v = temp.pop();
                if (v.equals("(")) {
                    if (!temp.contains("(")) {
                        gotRightOpeningParenthesis = true;
                    } else {
                        input.add(v);
                    }
                } else {
                    input.add(v);
                }
            }

            List<String> splittedExpression = new ArrayList<>();
            while (!temp.isEmpty() && !temp.peek().equals(")")) {
                splittedExpression.add(temp.pop());
            }
//            splittedExpression.add();

            input.add(String.valueOf(evaluateExpression(splittedExpression).intValue()));
            temp.pop();
            while (!temp.isEmpty()) {
                input.add(temp.pop());
            }
        }


        // STEP 2 - calculate * and /   -> prioritized operators
        while (input.contains("*") || input.contains("/")) { // TODO: set to prioritized ops
            // PREPARE TMP
            while (!input.isEmpty()) {
                temp.add(input.pop());
            }
            // HANDLE PARENTHESIS
            while (!temp.isEmpty() && !temp.peek().equals("*") && !temp.peek().equals("/")) {
                input.add(temp.pop());
            }

            if (temp.peek().equals("*")) {
                temp.pop();
                input.add(String.valueOf(Integer.parseInt(input.pop()) * Integer.parseInt(temp.pop())));
            } else if (temp.peek().equals("/")) {
                temp.pop();
                input.add(String.valueOf(Integer.parseInt(input.pop()) / Integer.parseInt(temp.pop())));
            } else {
                System.out.println("BG BUG");
            }

            while (!temp.isEmpty()) {
                input.add(temp.pop());
            }
        }


        // STEP 3 - calculate + and -   -> regular operators
        while (input.contains("+") || input.contains("-")) { // TODO: set to prioritized ops
            // PREPARE TMP
            while (!input.isEmpty()) {
                temp.add(input.pop());
            }
            // HANDLE PARENTHESIS
            while (!temp.isEmpty() && !temp.peek().equals("+") && !temp.peek().equals("-")) {
                input.add(temp.pop());
            }

            if (temp.peek().equals("+")) {
                temp.pop();
                input.add(String.valueOf(Integer.parseInt(input.pop()) + Integer.parseInt(temp.pop())));
            } else if (temp.peek().equals("-")) {
                temp.pop();
                input.add(String.valueOf(Integer.parseInt(input.pop()) - Integer.parseInt(temp.pop())));
            } else {
                System.out.println("BG BUG");
            }

            while (!temp.isEmpty()) {
                input.add(temp.pop());
            }
        }

        return Long.parseLong(input.pop());
    }

    private Integer evaluateExpression(List<String> tokens) {
        var v = tokens
                .stream()
                .filter(s -> !(s.equals("(") || s.equals(")") || SUPPORTED_OPERATORS.containsKey(s)))
                .toList();

        return SUPPORTED_OPERATORS.get(tokens.stream().filter(s -> SUPPORTED_OPERATORS.containsKey(s)).findFirst().get())
                .apply(Integer.parseInt(v.get(0)), Integer.parseInt(v.get(1))).intValue();
    }
    private String modifyInputString() {
        return expressionToSolve.replaceAll("\\(", "( ").replaceAll("\\)", " )");
    }

    private LinkedHashMap<String, Long> getMappings(String expression) {
        return Arrays.stream(expression.split(" ")).collect(Collectors.groupingBy(String::valueOf, LinkedHashMap::new, Collectors.counting()));
    }

    private boolean containsParenthesis(LinkedHashMap<String, Long> tokensCounter) {
        return tokensCounter.containsKey("(") || tokensCounter.containsKey(")");
    }

}
