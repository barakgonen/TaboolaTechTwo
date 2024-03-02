package org.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquationSolver {
    private List<String> input;
    private Map<String, Number> calculatedResults;
    private List<AbstractSyntaxTree> expressions;
    private Collection<Equation> equations;

    public EquationSolver(List<String> input) {
        this.input = input;
        this.calculatedResults = new HashMap<>();
        this.equations = input.stream().map(Equation::new).toList();
    }

//    public boolean solve() {
//
//        return true;
//    }

    public Map<String, Long> solve() {
        return Map.of();
    }
}
