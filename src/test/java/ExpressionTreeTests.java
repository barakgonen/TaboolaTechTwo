import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

public class ExpressionTreeTests {




//
//    @Test
//    public void testMappings() {
//        LinkedHashMap<String, Long> map = Utils.getMappings(Utils.modifyInputString("5"));
//        Assertions.assertEquals(1, map.size());
//        Assertions.assertEquals(map.get("5"), 1);
//
//        map = Utils.getMappings(Utils.modifyInputString("5 + 3"));
//        Assertions.assertEquals(3, map.size());
//        Assertions.assertEquals(map.get("5"), 1);
//        Assertions.assertEquals(map.get("+"), 1);
//        Assertions.assertEquals(map.get("3"), 1);
//
//        map = Utils.getMappings(Utils.modifyInputString("5 + 3 + 1"));
//        Assertions.assertEquals(4, map.size());
//        Assertions.assertEquals(map.get("5"), 1);
//        Assertions.assertEquals(map.get("+"), 2);
//        Assertions.assertEquals(map.get("3"), 1);
//        Assertions.assertEquals(map.get("1"), 1);
//
//        map = Utils.getMappings(Utils.modifyInputString("15 + 13"));
//        Assertions.assertEquals(3, map.size());
//        Assertions.assertEquals(map.get("15"), 1);
//        Assertions.assertEquals(map.get("+"), 1);
//        Assertions.assertEquals(map.get("13"), 1);
//
//        map = Utils.getMappings(Utils.modifyInputString("5 - 3"));
//        Assertions.assertEquals(3, map.size());
//        Assertions.assertEquals(map.get("5"), 1);
//        Assertions.assertEquals(map.get("-"), 1);
//        Assertions.assertEquals(map.get("3"), 1);
//
//        map = Utils.getMappings(Utils.modifyInputString("5 / 3"));
//        Assertions.assertEquals(3, map.size());
//        Assertions.assertEquals(map.get("5"), 1);
//        Assertions.assertEquals(map.get("/"), 1);
//        Assertions.assertEquals(map.get("3"), 1);
//
//        map = Utils.getMappings(Utils.modifyInputString("5 * 3"));
//        Assertions.assertEquals(3, map.size());
//        Assertions.assertEquals(map.get("5"), 1);
//        Assertions.assertEquals(map.get("*"), 1);
//        Assertions.assertEquals(map.get("3"), 1);
//
//        map = Utils.getMappings(Utils.modifyInputString("(5 * 3)"));
//        Assertions.assertEquals(5, map.size());
//        Assertions.assertEquals(map.get("5"), 1);
//        Assertions.assertEquals(map.get("*"), 1);
//        Assertions.assertEquals(map.get("3"), 1);
//        Assertions.assertEquals(map.get(")"), 1);
//        Assertions.assertEquals(map.get("("), 1);
//
//        map = Utils.getMappings(Utils.modifyInputString("5 + (3 + 1) * 4"));
//        Assertions.assertEquals(8, map.size());
//        Assertions.assertEquals(map.get("5"), 1);
//        Assertions.assertEquals(map.get("+"), 2);
//        Assertions.assertEquals(map.get("("), 1);
//        Assertions.assertEquals(map.get("3"), 1);
//        Assertions.assertEquals(map.get("1"), 1);
//        Assertions.assertEquals(map.get(")"), 1);
//        Assertions.assertEquals(map.get("4"), 1);
//        Assertions.assertEquals(map.get("*"), 1);
//
//        map = Utils.getMappings(Utils.modifyInputString("5 + (3 + 1)"));
//        Assertions.assertEquals(6, map.size());
//        Assertions.assertEquals(map.get("5"), 1);
//        Assertions.assertEquals(map.get("+"), 2);
//        Assertions.assertEquals(map.get("("), 1);
//        Assertions.assertEquals(map.get("3"), 1);
//        Assertions.assertEquals(map.get("1"), 1);
//        Assertions.assertEquals(map.get(")"), 1);
//
//        map = Utils.getMappings(Utils.modifyInputString("5 + ((3 + 1) + 2)"));
//        Assertions.assertEquals(7, map.size());
//        Assertions.assertEquals(map.get("5"), 1);
//        Assertions.assertEquals(map.get("+"), 3);
//        Assertions.assertEquals(map.get("("), 2);
//        Assertions.assertEquals(map.get("3"), 1);
//        Assertions.assertEquals(map.get("1"), 1);
//        Assertions.assertEquals(map.get(")"), 2);
//        Assertions.assertEquals(map.get("2"), 1);
//    }
//
//    @Test
//    public void testModifyInputString() {
//        String inputString = "5 + ((3 + 1) + 2)";
//        Assertions.assertEquals("5 + ( ( 3 + 1 ) + 2 )", Utils.modifyInputString(inputString));
//    }
}
