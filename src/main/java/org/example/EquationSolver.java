package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class EquationSolver {
    private static final Logger LOGGER = LogManager.getLogger();

    private List<String> input;
    private Map<String, Long> calculatedResults;
    private List<AbstractSyntaxTree> expressions;
    private Collection<Equation> equations;

    public EquationSolver(List<String> input) throws InvalidSyntaxException {
        this.input = input;
        this.calculatedResults = new ConcurrentHashMap<>();
        this.equations = new ArrayList<>();
        for (String s : input) {
            this.equations.add(new Equation(this.calculatedResults, s));
        }
    }

    public Map<String, Long> solve() {

        // TODO First iteration for immediate values only!
        List<CompletableFuture<Optional<EquationSolution>>> immediateSolutions = this.equations.stream()
                .filter(Equation::hasImmediateSolution)
                .map(equation -> CompletableFuture.supplyAsync(equation::solve))
                .toList();

        LOGGER.info("Found: {} equations that can be solved parallel since they have an immidiate solution", immediateSolutions.size());
        while (!immediateSolutions.stream().allMatch(CompletableFuture::isDone)) {
        }

        // Later, do the following -> try to solve one by one
        while (this.equations.stream().anyMatch(equation -> !equation.isSolved)) {
            LOGGER.info("Solving in progress, so far solved: {} out of: {} equations", equations.stream().filter(Equation::isSolved).count(), input.size());
            this.equations
                    .stream()
                    .filter(equation -> !equation.isSolved())
                    .forEach(Equation::solve);
        }

        LOGGER.info("Solving process has completed! solved: {} out of: {} equations", equations.stream().filter(Equation::isSolved).count(), input.size());

        return calculatedResults;
    }
}
