import org.example.Expression;
import org.example.InvalidSyntaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ExpressionTests {
    @ParameterizedTest
    @MethodSource("expectedResultToExpressionStr")
    void monthNames(int expectedResult, String expression) throws InvalidSyntaxException {
        Assertions.assertEquals(expectedResult, new Expression(expression).solve());
    }

    private static Stream<Arguments> expectedResultToExpressionStr() {
        return Stream.of(
                arguments(5, "5"),
                arguments(5 + 3, ("5 + 3")),
                arguments(5 + 3 + 1, ("5 + 3 + 1")),
                arguments(15 + 13, ("15 + 13")),
                arguments(5 - 3, ("5 - 3")),
                arguments(5 / 3, ("5 / 3")),
                arguments(5 * 3, ("5 * 3")),
                arguments(8, ("8")),
                arguments(5 + (3 + 1) * 4, ("5 + (3 + 1) * 4")),
                arguments(5 + (3 + 1), ("5 + (3 + 1)")),
                arguments(4 * (2 + 1), ("4 * (2 + 1)")),
                arguments(5 + ((3 + 1) + 2), ("5 + ((3 + 1) + 2)")),
                arguments((5 + 3) * 10, ("(5 + 3) * 10")),
                arguments(5 + 3 * 10, ("5 + 3 * 10")),
                arguments(5 + (3 * 10), ("5 + (3 * 10)")),
                arguments((3 + 1) * 4, ("(3 + 1) * 4")),
                arguments(5 + 4 * (2 + 1) + 3, ("5 + 4 * (2 + 1) + 3")),
                arguments(5 + 3 + (2 + 1) * 4, ("5 + 3 + (2 + 1) * 4")),
                arguments((5 - 3) - (4 - 1), ("(5 - 3) - (4 - 1)")),
                arguments(4 - (5 / 3), ("4 - (5 / 3)")),
                arguments((5 * 3) + 5, ("(5 * 3) + 5")),
                arguments(5 + 4 * (2 + 1) + 3, ("5 + 4 * (2 + 1) + 3")),
                arguments((5 + 3) * 2, ("(5 + 3) * 2")),
                arguments(10 - (4 * 2), ("10 - (4 * 2)")),
                arguments(6 / (2 + 1), ("6 / (2 + 1)")),
                arguments(8 - 3 / 4, ("8 - 3 / 4")),
                arguments((8 - 3) / 4, ("(8 - 3) / 4")),
                arguments((((5 + 3) * (2 - 1)) + (4 / 2)), ("(((5 + 3) * (2 - 1)) + (4 / 2))")),
                arguments((((10 / 2) + (4 * 3)) - (6 / 2)), ("(((10 / 2) + (4 * 3)) - (6 / 2))")),
                arguments((((6 * 2) - (4 + 1)) * (7 - 3)), ("(((6 * 2) - (4 + 1)) * (7 - 3))")),
                arguments(((((8 / 2) * 3) - 5) + (9 / 3)), ("((((8 / 2) * 3) - 5) + (9 / 3))")),
                arguments((((7 + 2) / (3 - 1)) * (4 + 2)), ("(((7 + 2) / (3 - 1)) * (4 + 2))")),
                arguments((((9 * 2) + (4 / 2)) - (5 / 1)), ("(((9 * 2) + (4 / 2)) - (5 / 1))")),
                arguments((((4 - 1) * (5 + 2)) + (10 / 5)), ("(((4 - 1) * (5 + 2)) + (10 / 5))")),
                arguments((((10 / 2) * (5 - 1)) - (8 / 4)), ("(((10 / 2) * (5 - 1)) - (8 / 4))")),
                arguments((((8 * 2) - (6 / 2)) * (7 + 3)), ("(((8 * 2) - (6 / 2)) * (7 + 3))")),
                arguments((((7 + 3) / (2 - 1)) + (6 * 2)), ("(((7 + 3) / (2 - 1)) + (6 * 2))")),
                arguments(((((6 * 3) + 2) / 4) * (5 - 1)), ("((((6 * 3) + 2) / 4) * (5 - 1))")),
                arguments((((5 + 2) * (9 / 3)) + (4 / 2)), ("(((5 + 2) * (9 / 3)) + (4 / 2))")),
                arguments((((4 - 1) * (6 + 2)) - (10 / 5)), ("(((4 - 1) * (6 + 2)) - (10 / 5))")),
                arguments((((10 / 2) + (4 * 3)) / (6 - 3)), ("(((10 / 2) + (4 * 3)) / (6 - 3))")),
                arguments((((6 * 2) - (4 + 1)) * (7 + 3)), ("(((6 * 2) - (4 + 1)) * (7 + 3))")),
                arguments(((((8 / 2) * 3) - 5) + (9 / 3)), ("((((8 / 2) * 3) - 5) + (9 / 3))")),
                arguments((((7 + 2) / (3 - 1)) * (4 + 2)), ("(((7 + 2) / (3 - 1)) * (4 + 2))")),
                arguments((((9 * 2) + (4 / 2)) - (5 / 1)), ("(((9 * 2) + (4 / 2)) - (5 / 1))")),
                arguments((((4 - 1) * (5 + 2)) + (10 / 5)), ("(((4 - 1) * (5 + 2)) + (10 / 5))")),
                arguments((((10 / 2) * (5 - 1)) - (8 / 4)), ("(((10 / 2) * (5 - 1)) - (8 / 4))")),
                arguments((((8 * 2) - (6 / 2)) * (7 + 3)), ("(((8 * 2) - (6 / 2)) * (7 + 3))")),
                arguments((((7 + 3) / (2 - 1)) + (6 * 2)), ("(((7 + 3) / (2 - 1)) + (6 * 2))")),
                arguments(((((6 * 3) + 2) / 4) * (5 - 1)), ("((((6 * 3) + 2) / 4) * (5 - 1))")),
                arguments((((5 + 2) * (9 / 3)) + (4 / 2)), ("(((5 + 2) * (9 / 3)) + (4 / 2))")),
                arguments((((4 - 1) * (6 + 2)) - (10 / 5)), ("(((4 - 1) * (6 + 2)) - (10 / 5))"))
        );
    }
}
