package ua.com.alevel.leveltwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCheck {

    public void validate(BufferedReader reader) throws IOException {
        String string;
        System.out.println("********** StringCheck **********");
        System.out.print("Please enter your string: ");
        while ((string = reader.readLine()) != null) {
            Pattern pattern = Pattern.compile("[(){}\\[\\]]");
            Matcher matcher = pattern.matcher(string);
            List<String> list = new ArrayList<>();
            while (matcher.find()) {
                String s = matcher.group();
                list.add(s);
            }
            while (list.contains("(")) {
                int start = list.indexOf("(");
                int end = list.lastIndexOf(")");
                if (start < end) {
                    list.remove(end);
                    list.remove(start);
                } else
                    break;
            }
            while (list.contains("{")) {
                int start = list.indexOf("{");
                int end = list.lastIndexOf("}");
                if (start < end) {
                    list.remove(end);
                    list.remove(start);
                } else
                    break;
            }
            while (list.contains("[")) {
                int start = list.indexOf("[");
                int end = list.lastIndexOf("]");
                if (start < end) {
                    list.remove(end);
                    list.remove(start);
                } else
                    break;
            }
            if (list.isEmpty()) {
                System.out.print("String correct!");
            } else {
                System.out.print("These brackets are unpaired: ");
                for (String s : list) {
                    System.out.print(s + " ");
                }
            }
            System.out.println();
            System.out.println("=========================================");
            System.out.println("If you want try again please press Enter");
            System.out.println("If you want return please enter 4");
            System.out.print("Make your choice: ");
            if (reader.readLine().equals("4"))
                return;
            System.out.print("Please enter your string: ");
        }
    }
}
