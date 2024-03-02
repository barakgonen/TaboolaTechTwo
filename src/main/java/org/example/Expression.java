package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static org.example.Constants.NON_PRIORITIZED_OPERATORS;
import static org.example.Constants.PRIORITIZED_OPERATORS;

/**
 * Represents arithmetic expression.
 * This class gets the expression to solve, and mapping of variables and tries to solve the equation
 */
public class Expression {
    private static final Logger LOGGER = LogManager.getLogger();

    private Map<String, Long> variableToResult;
    private String expressionToSolve;
    private Stack<String> input;
    private Stack<String> temp;

    /**
     * Constructor for multi equation system solution
     *
     * @param variableToResult  - mapping of variables to their value, for example i = 4
     * @param expressionToSolve - the expression to solve
     */
    public Expression(Map<String, Long> variableToResult, String expressionToSolve) {
        this.variableToResult = variableToResult;
        this.expressionToSolve = expressionToSolve;
        this.input = new Stack<>();
        this.temp = new Stack<>();
    }

    /**
     * Constructor for simple equation, without external dependencies, "self-contained" expression
     *
     * @param expressionToSolve - the expression to solve
     */
    public Expression(String expressionToSolve) {
        this(new HashMap<>(), expressionToSolve);
    }

    public Long solve() throws InvalidSyntaxException {
        LOGGER.debug("Trying to solve the following expression: {}", expressionToSolve);

        String modifiedExpression = modifyInputString();
        LinkedHashMap<String, Long> tokensCounter = getMappings(modifiedExpression);

        if (containsParenthesis(tokensCounter)) {
            if (!tokensCounter.get("(").equals(tokensCounter.get(")"))) {
                throw new InvalidSyntaxException("The following expression: " + expressionToSolve + ", is invalid expression, because number of ( is: " + tokensCounter.get("\"") + ", close: " + tokensCounter.get(")\\"));
            }
        }
        input.addAll(Arrays.asList(modifiedExpression.split(" ")));

        // STEP 1 - handle b
        parenthesis();

        // STEP 2 - calculate * and /   -> prioritized operators
        handlePrioritizedOperators();

        // STEP 3 - calculate + and -   -> regular operators
        handleRegularOperators();

        var calculatedValue = input.pop();
        if (isPostIncrementOperator(expressionToSolve)) {
            return runPostOperator(expressionToSolve);
        } else if (isPreIncrementOperator(expressionToSolve)) {
            return runPreOperator(expressionToSolve);
        } else if (variableToResult.containsKey(calculatedValue)) {
            return variableToResult.get(calculatedValue);
        } else {
            return Long.parseLong(calculatedValue);
        }
    }

    private void handleRegularOperators() throws InvalidSyntaxException {
        while (input.contains("+") || input.contains("-")) { // TODO: set to prioritized ops
            // PREPARE TMP
            while (!input.isEmpty()) {
                temp.add(input.pop());
            }
            // HANDLE PARENTHESIS
            while (!temp.isEmpty() && !temp.peek().equals("+") && !temp.peek().equals("-")) { // one of non, think about adding a method
                input.add(temp.pop());
            }

            calculateExpression(NON_PRIORITIZED_OPERATORS.get(temp.pop()));

            while (!temp.isEmpty()) {
                input.add(temp.pop());
            }
        }
    }

    private void handlePrioritizedOperators() throws InvalidSyntaxException {
        while (input.contains("*") || input.contains("/")) { // TODO: set to prioritized ops
            // PREPARE TMP
            while (!input.isEmpty()) {
                temp.add(input.pop());
            }
            // HANDLE PARENTHESIS
            while (!temp.isEmpty() && !temp.peek().equals("*") && !temp.peek().equals("/")) { // TODO: one of prioritized
                input.add(temp.pop());
            }

            calculateExpression(PRIORITIZED_OPERATORS.get(temp.pop()));

            while (!temp.isEmpty()) {
                input.add(temp.pop());
            }
        }
    }

    private void parenthesis() throws InvalidSyntaxException {
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

            input.add(String.valueOf(evaluateExpression(splittedExpression).intValue()));
            temp.pop();
            while (!temp.isEmpty()) {
                input.add(temp.pop());
            }
        }
    }

    private void calculateExpression(BiFunction<Number, Number, Number> function) throws InvalidSyntaxException {
        String lhs = input.pop();
        String rhs = temp.pop();
        input.add(String.valueOf(getCalculatedValue(lhs, rhs, function)));
    }

    private Long runPostOperator(String variable) throws InvalidSyntaxException {
        if (variable.contains("++")) {
            var value = variable.split("\\+\\+")[0];

            if (variableToResult.containsKey(value)) {
                Long valueToReturn = variableToResult.get(value);
                variableToResult.put(value, valueToReturn + 1);

                return valueToReturn;
            } else {
                throw new InvalidSyntaxException("The following expression is invalid: " + variable + ", since we can't use post increment operator in an immediate values");
            }
        } else if (variable.contains("--")) {
            var value = variable.split("\\-\\-")[0];

            if (variableToResult.containsKey(value)) {
                Long valueToReturn = variableToResult.get(value);
                variableToResult.put(value, valueToReturn - 1);

                return valueToReturn;
            } else {
                throw new InvalidSyntaxException("The following expression is invalid: " + variable + ", since we can't use post increment operator in an immediate values");
            }
        } else {
            throw new InvalidSyntaxException("Detected pre increment operator, but with no support, expression is: " + variable);
        }
    }

    private Long runPreOperator(String expr) throws InvalidSyntaxException {
        if (expr.contains("++")) {
            var value = expr.split("\\+\\+")[1];
            if (variableToResult.containsKey(value)) {
                variableToResult.put(value, variableToResult.get(value) + 1);

                return variableToResult.get(value);
            } else {
                throw new InvalidSyntaxException("The following expression is invalid: " + expr + ", since we can't use post increment operator in an immediate values");
            }
        } else if (expr.contains("--")) {
            var value = expr.split("\\-\\-")[1];
            if (variableToResult.containsKey(value)) {
                variableToResult.put(value, variableToResult.get(value) - 1);

                return variableToResult.get(value);
            } else {
                throw new InvalidSyntaxException("The following expression is invalid: " + expr + ", since we can't use post increment operator in an immediate values");
            }
        } else {
            throw new InvalidSyntaxException("Detected pre increment operator, but with no support, expression is: " + expr);
        }
    }

    private boolean isPostIncrementOperator(String expr) {
        return expr.endsWith("++") || expr.endsWith("--");
    }

    private boolean isPreIncrementOperator(String expr) {
        return expr.startsWith("++") || expr.startsWith("--");
    }

    private Integer getCalculatedValue(String lhs, String rhs, BiFunction<Number, Number, Number> function) throws InvalidSyntaxException {
        Long leftOperand = null;
        try {
            leftOperand = Long.parseLong(lhs);
        } catch (NumberFormatException e) {
            if (variableToResult.containsKey(lhs)) {
                leftOperand = variableToResult.get(lhs);
            } else if (isPreIncrementOperator(lhs)) {
                leftOperand = runPreOperator(lhs);
            } else if (isPostIncrementOperator(lhs)) {
                leftOperand = runPostOperator(lhs);
            } else {
                LOGGER.error("Couldn't translate the following value: {} to a number and it's not known variable", lhs);
            }
        }

        Long rightOperand = null;
        try {
            rightOperand = Long.parseLong(rhs);
        } catch (NumberFormatException e) {
            if (variableToResult.containsKey(rhs)) {
                rightOperand = variableToResult.get(rhs);
            } else if (isPreIncrementOperator(rhs)) {
                rightOperand = runPreOperator(rhs);
            } else if (isPostIncrementOperator(rhs)) {
                rightOperand = runPostOperator(rhs);
            } else {
                LOGGER.error("Couldn't translate the following value: {} to a number and it's not known variable", lhs);
            }
        }

        return (Integer) function.apply(leftOperand, rightOperand);
    }

    private Integer evaluateExpression(List<String> tokens) throws InvalidSyntaxException {
        var v = tokens
                .stream()
                .filter(s -> !(s.equals("(") || s.equals(")") || PRIORITIZED_OPERATORS.containsKey(s) || NON_PRIORITIZED_OPERATORS.containsKey(s)))
                .toList();

        var operator = tokens.stream().filter(s -> PRIORITIZED_OPERATORS.containsKey(s) || NON_PRIORITIZED_OPERATORS.containsKey(s)).findFirst().get();

        if (PRIORITIZED_OPERATORS.containsKey(operator)) {
            return getCalculatedValue(v.get(0), v.get(1), PRIORITIZED_OPERATORS.get(operator));
        } else {
            return getCalculatedValue(v.get(0), v.get(1), NON_PRIORITIZED_OPERATORS.get(operator));
        }
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
