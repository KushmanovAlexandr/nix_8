package ua.com.alevel;

import ua.com.alevel.char_from_string.CharFromString;
import ua.com.alevel.sum_of_string_numbers.SumOfStringNumbers;
import ua.com.alevel.end_of_lessons.EndOfLessons;

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
                    case "1" : {
                        new CharFromString().run(reader);
                    } break;
                    case "2" : {
                        new SumOfStringNumbers().run(reader);
                    } break;
                    case "3" : {
                        new EndOfLessons().run(reader);
                    } break;
                    case "0" : {
                        System.exit(0);
                    } break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void preview() {
        System.out.println("if you need first task, please select 1");
        System.out.println("if you need second task, please select 2");
        System.out.println("if you need third task, please select 3");
        System.out.println("if you need exit task, please select 0");
        System.out.println("Select you event:");
        System.out.println();
    }
}