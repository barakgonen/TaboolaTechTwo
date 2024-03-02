package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class Equation {

    private static final Logger LOGGER = LogManager.getLogger(Equation.class);

    private String expressionStr;
    private String variable;
    private Expression expression;

    public Equation(String expression) {
        this.expressionStr = expression;
        this.variable = expression.split("=")[0].replaceAll(" ", "");
        this.expression = new Expression(this.expressionStr);
    }

    public Optional<EquationSolution> solve() {
        try {
            return Optional.of(new EquationSolution(variable, expression.solve()));
        } catch (Exception e) {
            LOGGER.error("Caught an exception during trying to solve the equation: ", e);
        }

        return Optional.empty();
    }
}
