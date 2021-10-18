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
        char[] c_array = src.toCharArray();
        int pos_start = 0;
        int pos_end;
        char c, c_tmp;
        int i, j, rev_length;
        for (i = 0; i < c_array.length; i++) {
            c = c_array[i];
            if (c == ' ' || c == '\n') {
                if (pos_start != i) {
                    pos_end = i - 1;
                    rev_length = (i - pos_start) / 2;
                    for (j = 0; j < rev_length; j++) {
                        c_tmp = c_array[pos_start + j];
                        c_array[pos_start + j] = c_array[pos_end - j];
                        c_array[pos_end - j] = c_tmp;
                    }
                }
                pos_start = i + 1;
            }
        }
        //redundant, if only java had '\0' @ end of string
        if (pos_start != i) {
            pos_end = i - 1;
            rev_length = (i - pos_start) / 2;
            for (j = 0; j < rev_length; j++) {
                c_tmp = c_array[pos_start + j];
                c_array[pos_start + j] = c_array[pos_end - j];
                c_array[pos_end - j] = c_tmp;
            }
        }
        return new String(c_array);
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
        return src.substring(0, firstIndex) + reverse(src.substring(firstIndex, lastIndex)) + src.substring(lastIndex);
    }
}