package ua.com.alevel.levelone;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayOfNumbers {

    public void readLineParser(BufferedReader reader) throws IOException {
        String choice;
        System.out.println("********** ArrayOfNumbers **********");
        System.out.print("Please enter array numbers separated by any symbol: ");
        while ((choice = reader.readLine()) != null) {
            Set<Integer> uniqueNumber = new HashSet<>();
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(choice);
            while (matcher.find())
                uniqueNumber.add(Integer.parseInt(matcher.group()));
            System.out.println("Unique numbers in the array = " + uniqueNumber.size());
            System.out.println("=========================================");
            System.out.println("If you want try again please press Enter");
            System.out.println("If you want return please enter 0");
            System.out.print("Make your choice: ");
            if (reader.readLine().equals("0")) {
                return;
            } else {
                System.out.print("Please enter numbers: ");
                continue;
            }
        }
    }
}