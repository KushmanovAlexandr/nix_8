package ua.com.alevel.controller;

import ua.com.alevel.util.MathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MathSetController {
    private static final MathSet<Integer> numbers = new MathSet<>();
    private static final Scanner in = new Scanner(System.in);
    private static final MathSetController mathSetController = new MathSetController();

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите свой вариант");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                menu(position);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                menu(position);
            }
        } catch (IOException e) {
            System.out.println("ошибка: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("1. Добавить числа в MathSet,");
        System.out.println("2. Показать все элементы MathSet,");
        System.out.println("3. Сортировать по убыванию,");
        System.out.println("4. Сортировать по убыванию по индексам,");
        System.out.println("5. Сортировать по убыванию по значениям чисел,");
        System.out.println("6. Сортировать по возрастанию,");
        System.out.println("7. Сортировать по возрастанию по индексам,");
        System.out.println("8. Сортировать по возрастанию по значениям чисел,");
        System.out.println("9. Объеденить новый MathSet с текущим,");
        System.out.println("10. Вывести MIN (минимальное число),");
        System.out.println("11. Вывести MAX (максимальное число),");
        System.out.println("12. Вывести AVERAGE (среднее число),");
        System.out.println("13. Вывести MEDIAN (значение медианы),");
        System.out.println("14. Очистить MathSet,");
        System.out.println("15. Вырезать MathSet,");
        System.out.println("0. Выход");
        System.out.println();
    }

    private void menu(String position) {
        switch (position) {
            case "1":
                mathSetController.addNumbers(numbers);
                break;
            case "2":
                mathSetController.print();
                break;
            case "3":
                mathSetController.sortDesc();
                break;
            case "4":
                mathSetController.sortDescByIndexes();
                break;
            case "5":
                mathSetController.sortDescByNumbers();
                break;
            case "6":
                mathSetController.sortAsc();
                break;
            case "7":
                mathSetController.sortAscByIndexes();
                break;
            case "8":
                mathSetController.sortAscByNumbers();
                break;
            case "9":
                mathSetController.join();
                break;
            case "10":
                mathSetController.getMin();
                break;
            case "11":
                mathSetController.getMax();
                break;
            case "12":
                mathSetController.getAverage();
                break;
            case "13":
                mathSetController.getMedian();
                break;
            case "14":
                mathSetController.clear();
                break;
            case "15":
                mathSetController.cut();
                break;
            case "0":
                System.out.println("Выход");
                break;

        }
        runNavigation();
    }

    private static void addNumbers(MathSet<Integer> mathSet) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Введите количество чисел:");
            String count = sc.nextLine();
            for (int i = 0; i < Integer.parseInt(count); i++) {
                System.out.print("Введите число:");
                String s = sc.nextLine();
                Integer enteredValue = Integer.parseInt(s);
                mathSet.add(enteredValue);
            }
            printBeforeSorting();
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод");
        }
    }


    private static void sortDesc() {
        numbers.sortDesc();
        System.out.println("Отсортировано:");
        print();
    }

    private static void sortDescByIndexes() {
        printBeforeSorting();
        System.out.print("Введите первый индекс:");
        int firstIndex = in.nextInt();
        System.out.print("Введите второй индекс:");
        int secondIndex = in.nextInt();
        numbers.sortDesc(firstIndex, secondIndex);
        System.out.println("Отсортировано:");
        print();
    }

    private static void sortDescByNumbers() {
        printBeforeSorting();
        System.out.print("Введите число:");
        int number = in.nextInt();
        numbers.sortDesc(number);
        System.out.println("Отсортировано:");
        print();
    }

    private static void sortAsc() {
        numbers.sortAsc();
        System.out.println("Отсортировано:");
        print();
    }

    private static void sortAscByIndexes() {
        printBeforeSorting();
        System.out.print("Введите первый индекс:");
        int first = in.nextInt();
        System.out.print("Введите второй индекс:");
        int second = in.nextInt();
        numbers.sortAsc(first, second);
        System.out.println("Отсортировано:");
        print();
    }

    private static void sortAscByNumbers() {
        printBeforeSorting();
        System.out.print("Введите число:");
        int number = in.nextInt();
        numbers.sortAsc(number);
        System.out.println("Отсортировано:");
        print();
    }

    private static void join() {
        System.out.println("Создать новый MathSet");
        MathSet<Integer> ms = new MathSet<>();
        addNumbers(ms);
        numbers.join(new MathSet<Integer>(ms));
        print();
    }

    private static void getMin() {
        System.out.println("Минимальное число MathSet: " + numbers.getMin());
    }

    private static void getMax() {
        System.out.println("Максимальное число MathSet: " + numbers.getMax());
    }

    private static void getAverage() {
        System.out.println("Среднее число MathSet: " + numbers.getAverage());
    }

    private static void getMedian() {
        numbers.sortAsc();
        System.out.println("Значение медианы MathSet: " + numbers.getMedian());
    }

    private static void clear() {
        numbers.clear();
        print();
    }

    private static void cut() {
        while (true) {
            try {
                printBeforeSorting();
                System.out.print("Введите первый индекс:");
                int firstIndex = in.nextInt();
                System.out.print("Введите второй индекс:");
                int secondIndex = in.nextInt();
                System.out.print("Вырезано:");
                MathSet cutted = numbers.cut(firstIndex, secondIndex);
                for (int i = 0; i < cutted.size(); i++) {
                    System.out.print(" " + cutted.get(i));
                }
                System.out.println();
                break;
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Неверное значение!");
            }
        }
    }

    private static void print() {
        if (numbers.size() != 0) {
            for (int i = 0; i < numbers.size(); i++) {
                System.out.print(numbers.get(i) + " ");
            }
        } else {
            System.out.println("Пусто");
        }
    }

    private static void printBeforeSorting() {
        System.out.println("MathSet:");
        if (numbers.size() != 0) {
            for (int i = 0; i < numbers.size(); i++) {
                System.out.println(i + ":" + numbers.get(i));
            }
        } else {
            System.out.println("Пусто");
        }
    }


}