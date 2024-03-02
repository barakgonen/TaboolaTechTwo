package org.example;


import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public final class Constants {
    public static Map<String, BiFunction<Number, Number, Number>> SUPPORTED_OPERATORS = new HashMap<>() {{
        put("+", (number, number2) -> number.floatValue() + number2.floatValue());
        put("-", (number, number2) -> number.floatValue() - number2.floatValue());
        put("*", (number, number2) -> number.floatValue() * number2.floatValue());
        put("/", (number, number2) -> number.floatValue() / number2.floatValue());
    }};
}
