import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

public class ExpressionTreeTests {

    @Test
    public void testSingleValueExpression() {
        Assertions.assertEquals(5, Utils.calculateExpression("5"));
    }

    @Test
    public void testSimpleExpressionTreePlus() {
        Assertions.assertEquals(5 + 3, Utils.calculateExpression("5 + 3"));
        Assertions.assertEquals(5 + 3 + 1, Utils.calculateExpression("5 + 3 + 1"));
        Assertions.assertEquals(15 + 13, Utils.calculateExpression("15 + 13"));
    }

    @Test
    public void testSimpleExpressionTreeMinus() {
        Assertions.assertEquals(5 - 3, Utils.calculateExpression("5 - 3"));
    }

    @Test
    public void testSimpleExpressionTreeDiv() {
        Assertions.assertEquals(5 / 3, Utils.calculateExpression("5 / 3"));
    }

    @Test
    public void testSimpleExpressionTreeMul() {
        Assertions.assertEquals(5 * 3, Utils.calculateExpression("5 * 3"));
    }

    @Test
    public void testSimpleExpressionWithParentheses() {
        Assertions.assertEquals(8, Utils.calculateExpression("8"));
    }

    @Test
    public void testComplexExpressionTreePlus() {
        Assertions.assertEquals(5 + (3 + 1) * 4, Utils.calculateExpression("5 + (3 + 1) * 4"));
        Assertions.assertEquals(5 + (3 + 1), Utils.calculateExpression("5 + (3 + 1)"));
        Assertions.assertEquals(4 * (2 + 1), Utils.calculateExpression("4 * (2 + 1)"));
        Assertions.assertEquals(5 + ((3 + 1) + 2), Utils.calculateExpression("5 + ((3 + 1) + 2)"));
        Assertions.assertEquals((5 + 3) * 10, Utils.calculateExpression("(5 + 3) * 10"));
        Assertions.assertEquals(5 + 3 * 10, Utils.calculateExpression("5 + 3 * 10"));
        Assertions.assertEquals(5 + (3 * 10), Utils.calculateExpression("5 + (3 * 10)"));
        Assertions.assertEquals((3 + 1) * 4, Utils.calculateExpression("(3 + 1) * 4"));
        Assertions.assertEquals(5 + 4 * (2 + 1) + 3, Utils.calculateExpression("5 + 4 * (2 + 1) + 3"));
        Assertions.assertEquals(5 + 3 + (2 + 1) * 4, Utils.calculateExpression("5 + 3 + (2 + 1) * 4"));
    }

    @Test
    public void testComplexExpressionTreeMinus() {
        Assertions.assertEquals((5 - 3) - (4 - 1), Utils.calculateExpression("(5 - 3) - (4 - 1)"));
    }

    @Test
    public void testComplexExpressionTreeDiv() {
        Assertions.assertEquals(4 - (5 / 3), Utils.calculateExpression("4 - (5 / 3)"));
    }

    @Test
    public void testComplexExpressionTreeMul() {
        Assertions.assertEquals((5 * 3) + 5, Utils.calculateExpression("(5 * 3) + 5"));
    }

    @Test
    public void testMappings() {
        LinkedHashMap<String, Long> map = Utils.getMappings(Utils.modifyInputString("5"));
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals(map.get("5"), 1);

        map = Utils.getMappings(Utils.modifyInputString("5 + 3"));
        Assertions.assertEquals(3, map.size());
        Assertions.assertEquals(map.get("5"), 1);
        Assertions.assertEquals(map.get("+"), 1);
        Assertions.assertEquals(map.get("3"), 1);

        map = Utils.getMappings(Utils.modifyInputString("5 + 3 + 1"));
        Assertions.assertEquals(4, map.size());
        Assertions.assertEquals(map.get("5"), 1);
        Assertions.assertEquals(map.get("+"), 2);
        Assertions.assertEquals(map.get("3"), 1);
        Assertions.assertEquals(map.get("1"), 1);

        map = Utils.getMappings(Utils.modifyInputString("15 + 13"));
        Assertions.assertEquals(3, map.size());
        Assertions.assertEquals(map.get("15"), 1);
        Assertions.assertEquals(map.get("+"), 1);
        Assertions.assertEquals(map.get("13"), 1);

        map = Utils.getMappings(Utils.modifyInputString("5 - 3"));
        Assertions.assertEquals(3, map.size());
        Assertions.assertEquals(map.get("5"), 1);
        Assertions.assertEquals(map.get("-"), 1);
        Assertions.assertEquals(map.get("3"), 1);

        map = Utils.getMappings(Utils.modifyInputString("5 / 3"));
        Assertions.assertEquals(3, map.size());
        Assertions.assertEquals(map.get("5"), 1);
        Assertions.assertEquals(map.get("/"), 1);
        Assertions.assertEquals(map.get("3"), 1);

        map = Utils.getMappings(Utils.modifyInputString("5 * 3"));
        Assertions.assertEquals(3, map.size());
        Assertions.assertEquals(map.get("5"), 1);
        Assertions.assertEquals(map.get("*"), 1);
        Assertions.assertEquals(map.get("3"), 1);

        map = Utils.getMappings(Utils.modifyInputString("(5 * 3)"));
        Assertions.assertEquals(5, map.size());
        Assertions.assertEquals(map.get("5"), 1);
        Assertions.assertEquals(map.get("*"), 1);
        Assertions.assertEquals(map.get("3"), 1);
        Assertions.assertEquals(map.get(")"), 1);
        Assertions.assertEquals(map.get("("), 1);

        map = Utils.getMappings(Utils.modifyInputString("5 + (3 + 1) * 4"));
        Assertions.assertEquals(8, map.size());
        Assertions.assertEquals(map.get("5"), 1);
        Assertions.assertEquals(map.get("+"), 2);
        Assertions.assertEquals(map.get("("), 1);
        Assertions.assertEquals(map.get("3"), 1);
        Assertions.assertEquals(map.get("1"), 1);
        Assertions.assertEquals(map.get(")"), 1);
        Assertions.assertEquals(map.get("4"), 1);
        Assertions.assertEquals(map.get("*"), 1);

        map = Utils.getMappings(Utils.modifyInputString("5 + (3 + 1)"));
        Assertions.assertEquals(6, map.size());
        Assertions.assertEquals(map.get("5"), 1);
        Assertions.assertEquals(map.get("+"), 2);
        Assertions.assertEquals(map.get("("), 1);
        Assertions.assertEquals(map.get("3"), 1);
        Assertions.assertEquals(map.get("1"), 1);
        Assertions.assertEquals(map.get(")"), 1);

        map = Utils.getMappings(Utils.modifyInputString("5 + ((3 + 1) + 2)"));
        Assertions.assertEquals(7, map.size());
        Assertions.assertEquals(map.get("5"), 1);
        Assertions.assertEquals(map.get("+"), 3);
        Assertions.assertEquals(map.get("("), 2);
        Assertions.assertEquals(map.get("3"), 1);
        Assertions.assertEquals(map.get("1"), 1);
        Assertions.assertEquals(map.get(")"), 2);
        Assertions.assertEquals(map.get("2"), 1);
    }

    @Test
    public void testModifyInputString() {
        String inputString = "5 + ((3 + 1) + 2)";
        Assertions.assertEquals("5 + ( ( 3 + 1 ) + 2 )", Utils.modifyInputString(inputString));
    }

    @Test
    public void testWithStack() {
        Assertions.assertEquals(5 + 4 * (2 + 1) + 3, Utils.calculateExpression("5 + 4 * (2 + 1) + 3"));
    }

    @Test
    public void testGeneratedExpressions() {
        Assertions.assertEquals((5 + 3) * 2, Utils.calculateExpression("(5 + 3) * 2"));
        Assertions.assertEquals(10 - (4 * 2), Utils.calculateExpression("10 - (4 * 2)"));
        Assertions.assertEquals(6 / (2 + 1), Utils.calculateExpression("6 / (2 + 1)"));
        Assertions.assertEquals(8 - 3 / 4, Utils.calculateExpression("8 - 3 / 4"));
        Assertions.assertEquals((8 - 3) / 4, Utils.calculateExpression("(8 - 3) / 4"));
        Assertions.assertEquals((((5 + 3) * (2 - 1)) + (4 / 2)), Utils.calculateExpression("(((5 + 3) * (2 - 1)) + (4 / 2))"));
        Assertions.assertEquals((((10 / 2) + (4 * 3)) - (6 / 2)), Utils.calculateExpression("(((10 / 2) + (4 * 3)) - (6 / 2))"));
        Assertions.assertEquals((((6 * 2) - (4 + 1)) * (7 - 3)), Utils.calculateExpression("(((6 * 2) - (4 + 1)) * (7 - 3))"));
        Assertions.assertEquals(((((8 / 2) * 3) - 5) + (9 / 3)), Utils.calculateExpression("((((8 / 2) * 3) - 5) + (9 / 3))"));
        Assertions.assertEquals((((7 + 2) / (3 - 1)) * (4 + 2)), Utils.calculateExpression("(((7 + 2) / (3 - 1)) * (4 + 2))"));
        Assertions.assertEquals((((9 * 2) + (4 / 2)) - (5 / 1)), Utils.calculateExpression("(((9 * 2) + (4 / 2)) - (5 / 1))"));
        Assertions.assertEquals((((4 - 1) * (5 + 2)) + (10 / 5)), Utils.calculateExpression("(((4 - 1) * (5 + 2)) + (10 / 5))"));
        Assertions.assertEquals((((10 / 2) * (5 - 1)) - (8 / 4)), Utils.calculateExpression("(((10 / 2) * (5 - 1)) - (8 / 4))"));
        Assertions.assertEquals((((8 * 2) - (6 / 2)) * (7 + 3)), Utils.calculateExpression("(((8 * 2) - (6 / 2)) * (7 + 3))"));
        Assertions.assertEquals((((7 + 3) / (2 - 1)) + (6 * 2)), Utils.calculateExpression("(((7 + 3) / (2 - 1)) + (6 * 2))"));
        Assertions.assertEquals(((((6 * 3) + 2) / 4) * (5 - 1)), Utils.calculateExpression("((((6 * 3) + 2) / 4) * (5 - 1))"));
        Assertions.assertEquals((((5 + 2) * (9 / 3)) + (4 / 2)), Utils.calculateExpression("(((5 + 2) * (9 / 3)) + (4 / 2))"));
        Assertions.assertEquals((((4 - 1) * (6 + 2)) - (10 / 5)), Utils.calculateExpression("(((4 - 1) * (6 + 2)) - (10 / 5))"));
        Assertions.assertEquals((((10 / 2) + (4 * 3)) / (6 - 3)), Utils.calculateExpression("(((10 / 2) + (4 * 3)) / (6 - 3))"));
        Assertions.assertEquals((((6 * 2) - (4 + 1)) * (7 + 3)), Utils.calculateExpression("(((6 * 2) - (4 + 1)) * (7 + 3))"));
        Assertions.assertEquals(((((8 / 2) * 3) - 5) + (9 / 3)), Utils.calculateExpression("((((8 / 2) * 3) - 5) + (9 / 3))"));
        Assertions.assertEquals((((7 + 2) / (3 - 1)) * (4 + 2)), Utils.calculateExpression("(((7 + 2) / (3 - 1)) * (4 + 2))"));
        Assertions.assertEquals((((9 * 2) + (4 / 2)) - (5 / 1)), Utils.calculateExpression("(((9 * 2) + (4 / 2)) - (5 / 1))"));
        Assertions.assertEquals((((4 - 1) * (5 + 2)) + (10 / 5)), Utils.calculateExpression("(((4 - 1) * (5 + 2)) + (10 / 5))"));
        Assertions.assertEquals((((10 / 2) * (5 - 1)) - (8 / 4)), Utils.calculateExpression("(((10 / 2) * (5 - 1)) - (8 / 4))"));
        Assertions.assertEquals((((8 * 2) - (6 / 2)) * (7 + 3)), Utils.calculateExpression("(((8 * 2) - (6 / 2)) * (7 + 3))"));
        Assertions.assertEquals((((7 + 3) / (2 - 1)) + (6 * 2)), Utils.calculateExpression("(((7 + 3) / (2 - 1)) + (6 * 2))"));
        Assertions.assertEquals(((((6 * 3) + 2) / 4) * (5 - 1)), Utils.calculateExpression("((((6 * 3) + 2) / 4) * (5 - 1))"));
        Assertions.assertEquals((((5 + 2) * (9 / 3)) + (4 / 2)), Utils.calculateExpression("(((5 + 2) * (9 / 3)) + (4 / 2))"));
        Assertions.assertEquals((((4 - 1) * (6 + 2)) - (10 / 5)), Utils.calculateExpression("(((4 - 1) * (6 + 2)) - (10 / 5))"));
    }

}
