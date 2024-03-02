package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;

import static org.example.Constants.*;


public class Equation {

    private static final Logger LOGGER = LogManager.getLogger();

    private String originEquation;
    private String expressionStr;
    private String variable;
    private Expression expression;
    boolean isSolved;
    boolean isSolutionImmediate;
    private Map<String, Long> calculatedResults;
    private final String assignmentOperator;

    public Equation(String expression) throws InvalidSyntaxException {
        this(new HashMap<>(), expression);
    }

    public Equation(Map<String, Long> calculatedResults, String equationStr) throws InvalidSyntaxException {
        this.originEquation = equationStr;
        Matcher matcher = PATTERN.matcher(this.originEquation);
        if (matcher.matches()) {
            this.variable = matcher.group(1).replaceAll(" ", "");
            this.assignmentOperator = matcher.group(2);
            this.expressionStr = matcher.group(3);
            this.calculatedResults = calculatedResults;
            this.expression = new Expression(this.calculatedResults, this.expressionStr);
            this.isSolved = false;
            this.isSolutionImmediate = isSolutionImmediate();
        } else {
            throw new InvalidSyntaxException("Can't parse expression: " + equationStr);
        }


    }

    private boolean isSolutionImmediate() {
        for (int i = 0; i < this.expressionStr.length(); i++) {
            if (!(Character.isDigit(expressionStr.charAt(i))
                    || PRIORITIZED_OPERATORS.containsKey(String.valueOf(expressionStr.charAt(i)))
                    || NON_PRIORITIZED_OPERATORS.containsKey(String.valueOf(expressionStr.charAt(i)))
                    || expressionStr.charAt(i) == ')'
                    || expressionStr.charAt(i) == '('
                    || expressionStr.charAt(i) == ' ')) {
                LOGGER.info("The following expression: {}, does not have an immediate solution", originEquation);

                return false;
            }
        }

        LOGGER.debug("Expression: {} have an immediate solution", originEquation);

        return true;
    }

    public Optional<EquationSolution> solve() {
        LOGGER.info("Solving the following equation: {}", originEquation);
        try {
            Long result = expression.solve();
            this.isSolved = true;

            switch (assignmentOperator) {
                case "=":
                    this.calculatedResults.put(variable, result);
                    break;
                case "+=":
                    this.calculatedResults.put(variable, this.calculatedResults.get(variable) + result);
                    break;
                case "-=":
                    this.calculatedResults.put(variable, this.calculatedResults.get(variable) - result);
                    break;
                case "/=":
                    this.calculatedResults.put(variable, this.calculatedResults.get(variable) / result);
                    break;
                case "*=":
                    this.calculatedResults.put(variable, this.calculatedResults.get(variable) * result);
                    break;
                default:
                    LOGGER.error("Not familiar with requested assignmentOperator: {}", assignmentOperator);
                    return Optional.empty();
            }

            return Optional.of(new EquationSolution(variable, this.calculatedResults.get(variable)));
        } catch (Exception e) {
            LOGGER.error("Caught an exception during trying to solve the equation: ", e);
        }

        return Optional.empty();
    }

    public boolean isSolved() {
        return isSolved;
    }

    public boolean hasImmediateSolution() {
        return isSolutionImmediate;
    }

}
