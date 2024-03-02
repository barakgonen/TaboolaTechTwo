import jdk.jfr.Description;
import org.example.EquationSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ExpressionsWithVariablesCalculatorTests {

    @Test
    public void testOneEquation() {
        List<String> inputEquations = List.of("y = (5 + 3) * 10");
        EquationSolver equationSolver = new EquationSolver(inputEquations);

        Map<String, Long> solvedEquation = equationSolver.solve();

        Assertions.assertEquals(1, solvedEquation.size());
        Assertions.assertTrue(solvedEquation.containsKey("y"));
        Assertions.assertEquals((5 + 3) * 10, solvedEquation.get("y"));
    }
}
