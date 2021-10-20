package ua.com.alevel;


import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StringsMain {

    public static void main(String[] args) {
        int method = 0;
        int indexStart = 0;
        int indexFinish = 0;
        String line = "";
        String subLine = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("""
                    Reverse method:\s
                    if you want to use normal reverse, select "1";\s
                    if you want to reverse the specified substring in the string, select "2";\s
                    if you want to use reverse with a character, index or string, select "3";\s
                    if you want exit, select "0".\s
                    Select your method...""");
            try {
                method = WithNoExceptions(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (method) {
                case 0 -> System.out.println("Exit");
                case 1 -> {
                    System.out.println("Enter your string...");
                    try {
                        line = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(reverse(line));
                }
                case 2 -> {
                    System.out.println("Enter your string, then a substring...");
                    try {
                        line = reader.readLine();
                        subLine = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(reverse(line, subLine));
                }
                case 3 -> {
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
                }
                default -> System.out.println("Error! Incorrect choice of method!");
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

        String[] srcStringArray = src.split(" ");
        StringBuilder srcBuilder = new StringBuilder();
        for (int i = 0; i < srcStringArray.length; i++) {
            char[] charArray = srcStringArray[i].toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                srcBuilder.append(charArray[charArray.length - j - 1]);
            }
            if (i < srcStringArray.length - 1)
                srcBuilder.append(" ");
        }
        src = srcBuilder.toString();
        return src;
    }


    public static String reverse(String src, String dest) {

        return src.replaceAll(dest, reverse(dest));
    }


    public static String reverse(String src, int firstIndex, int lastIndex) {

        return src.replace(src.substring(firstIndex, lastIndex + 1), reverse(src.substring(firstIndex, lastIndex + 1)));
    }
}