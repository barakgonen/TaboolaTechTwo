package org.example;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

public final class Constants {
    public static Map<String, BiFunction<Number, Number, Number>> SUPPORTED_OPERATORS = new HashMap<>() {{
        put("+", (number, number2) -> number.intValue() + number2.intValue());
        put("-", (number, number2) -> number.intValue() - number2.intValue());
        put("*", (number, number2) -> number.intValue() * number2.intValue());
        put("/", (number, number2) -> number.intValue() / number2.intValue());
    }};

    public static final Pattern PATTERN = Pattern.compile("([a-zA-Z]+)\\s*([+\\-*/%&|^]?=)\\s*(.*)");

}
