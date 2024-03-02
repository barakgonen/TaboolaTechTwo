import org.example.EquationSolver;
import org.example.InvalidSyntaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExpressionsWithVariablesCalculatorTests {

    @Test
    public void testEquationWithImmediateValuesOnly() throws InvalidSyntaxException {
        List<String> equationSystem = List.of("y = (5 + 3) * 10");
        EquationSolver equationSolver = new EquationSolver(equationSystem);

        Map<String, Long> solvedEquation = equationSolver.solve();

        Assertions.assertEquals(1, solvedEquation.size());
        Assertions.assertTrue(solvedEquation.containsKey("y"));
        Assertions.assertEquals((5 + 3) * 10, solvedEquation.get("y"));
    }

    @Test
    public void testEquationSystemWithTwoEquationsWithImmediateValuesOnly() throws InvalidSyntaxException {
        List<String> equationSystem = List.of("y = (5 + 3) * 10", "i = 0");
        EquationSolver equationSolver = new EquationSolver(equationSystem);

        Map<String, Long> solvedEquation = equationSolver.solve();

        Assertions.assertEquals(2, solvedEquation.size());

        Assertions.assertTrue(solvedEquation.containsKey("y"));
        Assertions.assertEquals((5 + 3) * 10, solvedEquation.get("y"));

        Assertions.assertTrue(solvedEquation.containsKey("i"));
        Assertions.assertEquals(0, solvedEquation.get("i"));
    }

    @Test
    public void testEquationSystemWithTwoEquationsWhenJustFirstHasAnImmediateValue() throws InvalidSyntaxException {
        List<String> equationSystem = List.of("y = (5 + 3) * 10", "i = y");
        EquationSolver equationSolver = new EquationSolver(equationSystem);

        Map<String, Long> solvedEquation = equationSolver.solve();

        Assertions.assertEquals(2, solvedEquation.size());

        Assertions.assertTrue(solvedEquation.containsKey("y"));
        Assertions.assertEquals((5 + 3) * 10, solvedEquation.get("y"));

        Assertions.assertTrue(solvedEquation.containsKey("i"));
        Assertions.assertEquals(solvedEquation.get("y"), solvedEquation.get("i"));
    }

    @Test
    public void testEquationSystemWithThreeEquationsWhenJustOneHasAnImmediateValueAndSolutionsAreDependent() throws InvalidSyntaxException {
        List<String> equationSystem = List.of("x = i + 5", "y = (5 + 3) * 10", "i = y");
        EquationSolver equationSolver = new EquationSolver(equationSystem);

        Map<String, Long> solvedEquation = equationSolver.solve();

        Assertions.assertEquals(3, solvedEquation.size());

        Assertions.assertTrue(solvedEquation.containsKey("y"));
        Assertions.assertEquals((5 + 3) * 10, solvedEquation.get("y"));

        Assertions.assertTrue(solvedEquation.containsKey("i"));
        Assertions.assertEquals(solvedEquation.get("y"), solvedEquation.get("i"));

        Assertions.assertTrue(solvedEquation.containsKey("x"));
        Assertions.assertEquals(solvedEquation.get("i") + 5, solvedEquation.get("x"));
    }

    @Test
    public void testEquationsWithPreAndPostIncrementOperators() throws InvalidSyntaxException {
        List<String> equationSystem = List.of("i = j++ + ++j", "j = 4");
        EquationSolver equationSolver = new EquationSolver(equationSystem);

        Map<String, Long> solvedEquation = equationSolver.solve();

        Assertions.assertEquals(2, solvedEquation.size());

        // Calculate expected values
        int j = 4;
        int i = j++ + ++j;
        Assertions.assertTrue(solvedEquation.containsKey("j"));
        Assertions.assertEquals(j, solvedEquation.get("j"));

        Assertions.assertTrue(solvedEquation.containsKey("i"));
        Assertions.assertEquals(i, solvedEquation.get("i"));
    }

    @Test
    public void testExample() throws InvalidSyntaxException {
        List<String> equationSystem = List.of("i = 0", "j = ++i", "x = i++ + 5", "y = (5 + 3) * 10", "i += y");
        EquationSolver equationSolver = new EquationSolver(equationSystem);

        Map<String, Long> solvedEquation = equationSolver.solve();

        Assertions.assertEquals(4, solvedEquation.size());

        Assertions.assertTrue(solvedEquation.containsKey("i"));
        Assertions.assertEquals(82, solvedEquation.get("i"));

        Assertions.assertTrue(solvedEquation.containsKey("j"));
        Assertions.assertEquals(1, solvedEquation.get("j"));

        Assertions.assertTrue(solvedEquation.containsKey("x"));
        Assertions.assertEquals(6, solvedEquation.get("x"));

        Assertions.assertTrue(solvedEquation.containsKey("y"));
        Assertions.assertEquals(80, solvedEquation.get("y"));
    }


    @Test
    public void testGeneratedSystem() throws InvalidSyntaxException {
        List<String> equationSystem = List.of(
                "a = 0",
                "b = ++a",
                "c = a++ + 5",
                "d = (c + 3) * 10",
                "e = d - b",
                "f = e + 2",
                "g = f / 2",
                "h = ++g",
                "i = h++ + 7",
                "j = i * 3",
                "k = j / 2",
                "l = k - 4",
                "m = l * 5",
                "n = (m + 2) * 3",
                "o = n / 2",
                "p = o + 1",
                "q = p - 3",
                "r = q * 2",
                "s = r + 7",
                "t = s / 2",
                "u = t++ + 3",
                "v = u * 4",
                "w = (v - 2) * 2",
                "x = w + 5",
                "y = x + 10",
                "z = y / 2",
                "aa = z - 3",
                "ab = aa + 6",
                "ac = ab - 5",
                "ad = ac * 2"
        );

        EquationSolver equationSolver = new EquationSolver(equationSystem);

        Map<String, Long> solvedEquation = equationSolver.solve();

        Map<String, Long> expectedValues = Stream.of(
                        new AbstractMap.SimpleImmutableEntry<>("a", 2L),
                        new AbstractMap.SimpleImmutableEntry<>("b", 1L),
                        new AbstractMap.SimpleImmutableEntry<>("c", 6L),
                        new AbstractMap.SimpleImmutableEntry<>("d", 90L),
                        new AbstractMap.SimpleImmutableEntry<>("e", 89L),
                        new AbstractMap.SimpleImmutableEntry<>("f", 91L),
                        new AbstractMap.SimpleImmutableEntry<>("g", 46L),
                        new AbstractMap.SimpleImmutableEntry<>("h", 47L),
                        new AbstractMap.SimpleImmutableEntry<>("i", 53L),
                        new AbstractMap.SimpleImmutableEntry<>("j", 159L),
                        new AbstractMap.SimpleImmutableEntry<>("k", 79L),
                        new AbstractMap.SimpleImmutableEntry<>("l", 75L),
                        new AbstractMap.SimpleImmutableEntry<>("m", 375L),
                        new AbstractMap.SimpleImmutableEntry<>("n", 1131L),
                        new AbstractMap.SimpleImmutableEntry<>("o", 565L),
                        new AbstractMap.SimpleImmutableEntry<>("p", 566L),
                        new AbstractMap.SimpleImmutableEntry<>("q", 563L),
                        new AbstractMap.SimpleImmutableEntry<>("r", 1126L),
                        new AbstractMap.SimpleImmutableEntry<>("s", 1133L),
                        new AbstractMap.SimpleImmutableEntry<>("t", 567L),
                        new AbstractMap.SimpleImmutableEntry<>("u", 569L),
                        new AbstractMap.SimpleImmutableEntry<>("v", 2276L),
                        new AbstractMap.SimpleImmutableEntry<>("w", 4548L),
                        new AbstractMap.SimpleImmutableEntry<>("x", 4553L),
                        new AbstractMap.SimpleImmutableEntry<>("y", 4563L),
                        new AbstractMap.SimpleImmutableEntry<>("z", 2281L),
                        new AbstractMap.SimpleImmutableEntry<>("aa", 2278L),
                        new AbstractMap.SimpleImmutableEntry<>("ab", 2284L),
                        new AbstractMap.SimpleImmutableEntry<>("ac", 2279L),
                        new AbstractMap.SimpleImmutableEntry<>("ad", 4558L))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Assertions.assertEquals(expectedValues.keySet().size(), solvedEquation.size());

        expectedValues.forEach((key, value) -> Assertions.assertEquals(value, solvedEquation.get(key)));

    }
}
