package ua.com.alevel;

import ua.com.alevel.reversestring.AllReverseAndReverseOptionsWithIndexIndication;
import ua.com.alevel.reversestring.NormalReverse;
import ua.com.alevel.reversestring.ReverseOnTheSpecifiedSubstringInTheString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramRun {

    public static void run() {
        preview();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String event;
        try {
            while ((event = reader.readLine()) != null) {
                switch (event) {
                    case "1": {
                        new NormalReverse().run(reader);
                    }
                    break;
                    case "2": {
                        new ReverseOnTheSpecifiedSubstringInTheString().run(reader);
                    }
                    break;
                    case "3": {
                        new AllReverseAndReverseOptionsWithIndexIndication().run(reader);
                    }
                    break;
                    case "0": {
                        System.exit(0);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void preview() {
        System.out.println("if you want to use normal reverse, select 1");
        System.out.println("if you want to reverse on the specified substring in the string, select 2");
        System.out.println("if you want to use all of the above methods as well as a method with a character, index or string, select 3");
        System.out.println("if you need exit, please select 0");
        System.out.println("Select you method:");
        System.out.println();
    }
}