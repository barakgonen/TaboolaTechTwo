package org.example;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String questionArray = "10 + 4";
        //Lets say you have a string like this
//Lets find the Arithmetic sign first in the String
        String sign = null;

        Pattern regex = Pattern.compile("[+*/]|(?<=\\s)-");
        Matcher matcher = regex.matcher(questionArray);
        while(matcher.find()) {
            sign = matcher.group().trim();  //Store that Arithmetic sign here.
        }

        String [] parts = questionArray.split("[+*/]|(?<=\\s)-"); //Now break up the string using this regex.
        //This regex will find whatever Arithmetic sign
        //there is inside this String and store the result
        //in parts array.

        int firstNumber = Integer.parseInt(parts[0].replaceAll(" ", "")); //The first number in the String
        int secondNumber = Integer.parseInt(parts[1].replaceAll(" ", ""));//The second number in the String

//a simple if-else statements that will help us find the final answer.
        int result = 0;
        if (sign.equals("+")){
            result = firstNumber + secondNumber;
        } else if (sign.equals("-")) {
            result = firstNumber - secondNumber;
        } else if(sign.equals("*")) {
            result = firstNumber * secondNumber;
        } else if (sign.equals("/")){
            result = firstNumber / secondNumber;
        } else {
            System.out.println("unknown operation");
        }

        System.out.println(result);
    }
}