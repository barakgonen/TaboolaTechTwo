import org.example.AbstractSyntaxTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

public class ExpressionTreeTests {

    @Test
    public void testSingleValueExpression() {
//        Integer expectedInt = 5;
//        AbstractSyntaxTree abstractSyntaxTree = new AbstractSyntaxTree(String.valueOf(expectedInt));
//
//        Assertions.assertEquals(5, abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals(5, Utils.calculateExpression("5"));
    }

    @Test
    public void testSimpleExpressionTreePlus() {
//        AbstractSyntaxTree abstractSyntaxTree = new AbstractSyntaxTree("5 + 3");

//        Assertions.assertEquals(8, abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals(5 + 3, Utils.calculateExpression("5 + 3"));

//        abstractSyntaxTree = new AbstractSyntaxTree("5 + 3 + 1");

//        Assertions.assertEquals(5 + 3 + 1, abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals(5 + 3 + 1, Utils.calculateExpression("5 + 3 + 1"));

//        abstractSyntaxTree = new AbstractSyntaxTree("15 + 13");
//
//        Assertions.assertEquals(15 + 13, abstractSyntaxTree.solve().get().intValue());

        Assertions.assertEquals(15 + 13, Utils.calculateExpression("15 + 13"));
    }

    @Test
    public void testSimpleExpressionTreeMinus() {
//        AbstractSyntaxTree abstractSyntaxTree = new AbstractSyntaxTree("5 - 3");
//
//        Assertions.assertEquals(2, abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals(5 - 3, Utils.calculateExpression("5 - 3"));
    }

    @Test
    public void testSimpleExpressionTreeDiv() {
//        AbstractSyntaxTree abstractSyntaxTree = new AbstractSyntaxTree("5 / 3");

//        Assertions.assertEquals(5 / 3, abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals(5 / 3, Utils.calculateExpression("5 / 3"));
    }

    @Test
    public void testSimpleExpressionTreeMul() {
//        AbstractSyntaxTree abstractSyntaxTree = new AbstractSyntaxTree("5 * 3");

//        Assertions.assertEquals(15, abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals(5 * 3, Utils.calculateExpression("5 * 3"));
    }

    @Test
    public void testSimpleExpressionWithParentheses() {
//        AbstractSyntaxTree abstractSyntaxTree = new AbstractSyntaxTree("(5 + 3)");

//        Assertions.assertEquals(8, abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals(8, Utils.calculateExpression("8"));
    }

    @Test
    public void testComplexExpressionTreePlus() {
//        AbstractSyntaxTree abstractSyntaxTree = new AbstractSyntaxTree("5 + (3 + 1) * 4");
//        Assertions.assertEquals(5 + (3 + 1) * 4, abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals(5 + (3 + 1) * 4, Utils.calculateExpression("5 + (3 + 1) * 4"));
//
//        abstractSyntaxTree = new AbstractSyntaxTree("5 + (3 + 1)");
//        Assertions.assertEquals(5 + (3 + 1), abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals(5 + (3 + 1), Utils.calculateExpression("5 + (3 + 1)"));
//
//        abstractSyntaxTree = new AbstractSyntaxTree("4 * (2 + 1)");
//        Assertions.assertEquals(4 * (2 + 1), abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals(4 * (2 + 1), Utils.calculateExpression("4 * (2 + 1)"));
//        abstractSyntaxTree = new AbstractSyntaxTree("5 + ((3 + 1) + 2)");
//
//        Assertions.assertEquals(5 + ((3 + 1) + 2), abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals(5 + ((3 + 1) + 2), Utils.calculateExpression("5 + ((3 + 1) + 2)"));
//
//        abstractSyntaxTree = new AbstractSyntaxTree("(5 + 3) * 10");
//
//        Assertions.assertEquals((5 + 3) * 10, abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals((5 + 3) * 10, Utils.calculateExpression("(5 + 3) * 10"));
//
//        abstractSyntaxTree = new AbstractSyntaxTree("5 + 3 * 10");
//
//        Assertions.assertEquals(5 + 3 * 10, abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals(5 + 3 * 10, Utils.calculateExpression("5 + 3 * 10"));
//
//        abstractSyntaxTree = new AbstractSyntaxTree("5 + (3 * 10)");
//
//        Assertions.assertEquals(5 + 3 * 10, abstractSyntaxTree.solve().get().intValue());
//        Assertions.assertEquals(5 + (3 * 10), abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals(5 + (3 * 10), Utils.calculateExpression("5 + (3 * 10)"));
//        abstractSyntaxTree = new AbstractSyntaxTree("(3 + 1) * 4");
//
//        Assertions.assertEquals((3 + 1) * 4, abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals((3 + 1) * 4, Utils.calculateExpression("(3 + 1) * 4"));
//        abstractSyntaxTree = new AbstractSyntaxTree("5 + 4 * (2 + 1) + 3");
//        Assertions.assertEquals(5 + 4 * (2 + 1) + 3, abstractSyntaxTree.solve().get().intValue());
        Assertions.assertEquals(5 + 4 * (2 + 1) + 3, Utils.calculateExpression("5 + 4 * (2 + 1) + 3"));
//
//        abstractSyntaxTree = new AbstractSyntaxTree("5 + 3 + (2 + 1) * 4");
//        Assertions.assertEquals(5 + 3 + (2 + 1) * 4, abstractSyntaxTree.solve().get().intValue());

        Assertions.assertEquals(5 + 3 + (2 + 1) * 4, Utils.calculateExpression("5 + 3 + (2 + 1) * 4"));
    }

    @Test
    public void testComplexExpressionTreeMinus() {
//        AbstractSyntaxTree abstractSyntaxTree = new AbstractSyntaxTree("(5 - 3) - (4 - 1)");
//
//        Assertions.assertEquals((5 - 3) - (4 - 1), abstractSyntaxTree.solve());

        Assertions.assertEquals((5 - 3) - (4 - 1), Utils.calculateExpression("(5 - 3) - (4 - 1)"));
    }

    @Test
    public void testComplexExpressionTreeDiv() {
//        AbstractSyntaxTree abstractSyntaxTree = new AbstractSyntaxTree("4 - (5 / 3)");
//
//        Assertions.assertEquals(4 - (5 / 3), abstractSyntaxTree.solve());

        Assertions.assertEquals(4 - (5 / 3), Utils.calculateExpression("4 - (5 / 3)"));
    }

    @Test
    public void testComplexExpressionTreeMul() {
//        AbstractSyntaxTree abstractSyntaxTree = new AbstractSyntaxTree("(5 * 3) + 5");
//
//        Assertions.assertEquals(20, abstractSyntaxTree.solve().get().intValue());

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

}
