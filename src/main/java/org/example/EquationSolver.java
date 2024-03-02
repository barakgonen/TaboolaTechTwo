package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

public class EquationSolver {
    private List<String> input;
    private Map<String, Number> calculatedResults;
    private List<AbstractSyntaxTree> expressions;

    public EquationSolver(List<String> input) {
        this.input = input;
        this.expressions = input.stream().map(AbstractSyntaxTree::new).toList();
        this.calculatedResults = new HashMap<>();
    }

    public boolean solve() {

        return true;
    }
}
