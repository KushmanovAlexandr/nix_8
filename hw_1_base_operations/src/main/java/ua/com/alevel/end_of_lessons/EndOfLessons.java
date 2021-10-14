package ua.com.alevel.end_of_lessons;

import java.io.BufferedReader;
import java.io.IOException;

public class EndOfLessons {

    public void run(BufferedReader reader) throws IOException {
        System.out.println("Task3.run");
        System.out.println("Enter the lesson number from 1 to 10:");
        int countLesson = reader.read();
        int pauseLong = 15;
        int pauseShort = 5;
        int lessonEndMinutes = 60 * 9 + countLesson * 45 + (countLesson / 2) * pauseShort + ((countLesson - 1) / 2) * pauseLong;
        System.out.println("Выходные данные:");
        System.out.printf("%d %d", lessonEndMinutes / 60, lessonEndMinutes % 60);
        System.out.println();
        System.out.println("Select you event:");
    }
}
