package ua.com.alevel.end_of_lessons;

import java.io.BufferedReader;
import java.io.IOException;

public class EndOfLessons {

    public void run(BufferedReader reader) throws IOException {
        System.out.println("Task3.run");
        System.out.println("Enter the lesson number from 1 to 10:");
        int countLesson = 0;
        int[] array = new int[11];
        int except = 1;
        try {
            countLesson = Integer.parseInt(reader.readLine());
            except /= countLesson; // if (countLesson == 0) throw ArithmeticException
            except = array[countLesson]; // if (countLesson<0 && countLesson>10) throw ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException | NumberFormatException e) {
            System.out.println("Incorrect entry of the lesson number!");
            return;
        }
        int pauseLong = 15;
        int pauseShort = 5;
        int lessonEndMinutes = 60 * 9 + countLesson * 45 + (countLesson / 2) * pauseShort + ((countLesson - 1) / 2) * pauseLong;
        System.out.print("End time of the lesson ");
        System.out.printf("%d:%d", lessonEndMinutes / 60, lessonEndMinutes % 60);
        System.out.println();
        System.out.println("Select your event from 1 to 3 (0-exit):");
    }
}
