package ua.com.alevel.end_of_lessons;

import java.util.Scanner;

public class EndOfLessons {

    public void run() {
        System.out.println("Task3.run");
        Scanner sc = new Scanner(System.in);
        System.out.println("Входные данные:");
        int countLesson = sc.nextInt();
        int pauseLong = 15;
        int pauseShort = 5;
        int lessonEndMinutes = 60 * 9 + countLesson * 45 + (countLesson / 2) * pauseShort + ((countLesson - 1) / 2) * pauseLong;
        System.out.println("Выходные данные:");
        System.out.printf("%d %d", lessonEndMinutes / 60, lessonEndMinutes % 60);
    }
}
