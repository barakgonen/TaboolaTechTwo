package org.example;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.example.Constants.SUPPORTED_OPERATORS;

/**
 * AST - represents abstract syntax tree
 */
public class AbstractSyntaxTree {
    private TreeNode root;

    public AbstractSyntaxTree(String expr) {
        root = new TreeNode(expr);
    }

    public Optional<Number> solve() {
        return solve(root);
    }

    private Optional<Number> solve(TreeNode treeNode) {
        if (treeNode.leftOperand == null && treeNode.rightOperand == null) {
            if (treeNode.value != null) {
                return Optional.of(treeNode.value);
            } else {
                return Optional.empty();
            }
        }

        Optional<Number> left = solve(treeNode.leftOperand);
        Optional<BiFunction<Number, Number, Number>> operator = treeNode.function;
        Optional<Number> right = solve(treeNode.rightOperand);

        if (left.isPresent()) {
            if (right.isPresent()) {
                if (operator.isPresent()) {
                    return Optional.of(operator.get().apply(left.get(), right.get()));
                }
            }
        }

        return Optional.empty();  /// TODO understand all cases
    }
}
