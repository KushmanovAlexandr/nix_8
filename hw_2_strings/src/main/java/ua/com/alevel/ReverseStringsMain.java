package ua.com.alevel;


import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReverseStringsMain {

    public static void main(String[] args) {
        int method = 0;
        int indexStart = 0;
        int indexFinish = 0;
        String line = "";
        String subLine = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("Reverse method: \n" +
                    "if you want to use normal reverse, select \"1\"; \n" +
                    "if you want to reverse the specified substring in the string, select \"2\"; \n" +
                    "if you want to use reverse with a character, index or string, select \"3\"; \n" +
                    "if you want exit, select \"0\". \n" +
                    "Select your method...");
            try {
                method = WithNoExceptions(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (method) {
                case 0:
                    System.out.println("Exit");
                    break;
                case 1:
                    System.out.println("Enter your string...");
                    try {
                        line = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(reverse(line));
                    break;
                case 2:
                    System.out.println("Enter your string, then a substring...");
                    try {
                        line = reader.readLine();
                        subLine = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(reverse(line, subLine));
                    break;
                case 3:
                    System.out.println("Enter your string, then enter the first and last index...");
                    try {
                        line = reader.readLine();
                        indexStart = WithNoExceptions(reader.readLine());
                        indexFinish = WithNoExceptions(reader.readLine());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error! Index entered incorrectly!");
                    }
                    System.out.println(reverse(line, indexStart, indexFinish));
                    break;
                default:
                    System.out.println("Error! Incorrect choice of method!");
            }
        }
        while (method != 0);


    }

    public static int WithNoExceptions(String number) {
        int defaultValue = Integer.MAX_VALUE;
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static String reverse(String src) {

        String rightStr;
        String leftStr;
        int length = src.length();

        if (length <= 1) {
            return src;
        }
        leftStr = src.substring(0, length / 2);
        rightStr = src.substring(length / 2, length);

        return reverse(rightStr) + reverse(leftStr);
    }

    public static String reverse(String src, String dest) {
        String result;
        int index = src.indexOf(dest);
        if (index == -1) {
            result = "The substring is not part of the string!";
            return result;
        }
        result = src.substring(0, index) + reverse(dest) + src.substring(index + dest.length());
        return result;
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        if (firstIndex > src.length() || lastIndex > src.length()) {
            return "Error! Substring index must not exceed the size of the original string!";
        }
        if (firstIndex > lastIndex) {
            return "Error! The starting index must not be greater than the ending!";
        }
        String result = src.substring(0, firstIndex) + reverse(src.substring(firstIndex, lastIndex)) + src.substring(lastIndex);
        return result;
    }
}