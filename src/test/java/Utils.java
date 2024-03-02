import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static org.example.Constants.SUPPORTED_OPERATORS;

public class Utils {

//    public static int calculateExpression(String expression) {
//        /**
//         * 1 calculate all ()
//         * 2 calculate all * OR /
//         * 3 calculate all + OR -
//         *
//         */
//
//        String modifiedExpression = modifyInputString(expression);
//
//        Stack<String> input = new Stack<>();
//        Stack<String> temp = new Stack<>();
//
//        LinkedHashMap<String, Long> tokensCounter = getMappings(modifiedExpression);
//
//        if (containsParenthesis(tokensCounter)) {
//            if (!tokensCounter.get("(").equals(tokensCounter.get(")"))) {
//                System.out.println("This is not a valid expression, number of ( is: " + tokensCounter.get("(") + ", close: " + tokensCounter.get(")"));
//                return -9999;
//            }
//        }
//        input.addAll(Arrays.asList(modifiedExpression.split(" ")));
//
//        while (input.contains("(") && input.contains(")")) {
//            // PREPARE TMP
//            while (!input.isEmpty()) {
//                temp.add(input.pop());
//            }
//
//            boolean gotRightOpeningParenthesis = false;
//            // HANDLE PARENTHESIS
//            while (!temp.isEmpty() && !gotRightOpeningParenthesis) {
//                var v = temp.pop();
//                if (v.equals("(")) {
//                    if (!temp.contains("(")) {
//                        gotRightOpeningParenthesis = true;
//                    } else {
//                        input.add(v);
//                    }
//                } else {
//                    input.add(v);
//                }
//            }
//
//            List<String> splittedExpression = new ArrayList<>();
//            while (!temp.isEmpty() && !temp.peek().equals(")")) {
//                splittedExpression.add(temp.pop());
//            }
////            splittedExpression.add();
//
//            input.add(String.valueOf(evaluateExpression(splittedExpression).intValue()));
//            temp.pop();
//            while (!temp.isEmpty()) {
//                input.add(temp.pop());
//            }
//        }
//
//
//        // STEP 2 - calculate * and /   -> prioritized operators
//        while (input.contains("*") || input.contains("/")) { // TODO: set to prioritized ops
//            // PREPARE TMP
//            while (!input.isEmpty()) {
//                temp.add(input.pop());
//            }
//            // HANDLE PARENTHESIS
//            while (!temp.isEmpty() && !temp.peek().equals("*") && !temp.peek().equals("/")) {
//                input.add(temp.pop());
//            }
//
//            if (temp.peek().equals("*")) {
//                temp.pop();
//                input.add(String.valueOf(Integer.parseInt(input.pop()) * Integer.parseInt(temp.pop())));
//            } else if (temp.peek().equals("/")) {
//                temp.pop();
//                input.add(String.valueOf(Integer.parseInt(input.pop()) / Integer.parseInt(temp.pop())));
//            } else {
//                System.out.println("BG BUG");
//            }
//
//            while (!temp.isEmpty()) {
//                input.add(temp.pop());
//            }
//        }
//
//
//        // STEP 3 - calculate + and -   -> regular operators
//        while (input.contains("+") || input.contains("-")) { // TODO: set to prioritized ops
//            // PREPARE TMP
//            while (!input.isEmpty()) {
//                temp.add(input.pop());
//            }
//            // HANDLE PARENTHESIS
//            while (!temp.isEmpty() && !temp.peek().equals("+") && !temp.peek().equals("-")) {
//                input.add(temp.pop());
//            }
//
//            if (temp.peek().equals("+")) {
//                temp.pop();
//                input.add(String.valueOf(Integer.parseInt(input.pop()) + Integer.parseInt(temp.pop())));
//            } else if (temp.peek().equals("-")) {
//                temp.pop();
//                input.add(String.valueOf(Integer.parseInt(input.pop()) - Integer.parseInt(temp.pop())));
//            } else {
//                System.out.println("BG BUG");
//            }
//
//            while (!temp.isEmpty()) {
//                input.add(temp.pop());
//            }
//        }
//
//        return Integer.parseInt(input.pop());
//    }
//
//    private static Integer evaluateExpression(List<String> tokens) {
//        var v = tokens
//                .stream()
//                .filter(s -> !(s.equals("(") || s.equals(")") || SUPPORTED_OPERATORS.containsKey(s)))
//                .toList();
//
//        return SUPPORTED_OPERATORS.get(tokens.stream().filter(s -> SUPPORTED_OPERATORS.containsKey(s)).findFirst().get())
//                .apply(Integer.parseInt(v.get(0)), Integer.parseInt(v.get(1))).intValue();
//    }
//    static String modifyInputString(String originalExpression) {
//        return originalExpression.replaceAll("\\(", "( ").replaceAll("\\)", " )");
//    }
//
//    static LinkedHashMap<String, Long> getMappings(String expression) {
//        return Arrays.stream(expression.split(" ")).collect(Collectors.groupingBy(String::valueOf, LinkedHashMap::new, Collectors.counting()));
//    }
//
//    private static boolean containsParenthesis(LinkedHashMap<String, Long> tokensCounter) {
//        return tokensCounter.containsKey("(") || tokensCounter.containsKey(")");
//    }
}
