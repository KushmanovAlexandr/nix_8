package ua.com.alevel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModuleSecond {

    /**
     * 1. Дан список дат (строковая запись) в форматах типа “2020/04/05”, “05/04/2020”, “04-05-2020”
     * (все даты в примере - 5е апреля 2020)
     * Вернуть список дат (строковая запись) в формате “20200405”. Даты с неверным форматом - игнорировать.
     */
    public static boolean firstTask(String inputPath, String outputPath) {
        String fileInString = readFile(inputPath);
        List<String> dates = new ArrayList<>();
        String regDay = "(0[1-9]|[12]\\d|3[01])";
        String regMonth = "(0[1-9]|1[0-2])";
        String regYear = "([0-9]{4})";
        String regFirst = "(" + regYear + "/" + regMonth + "/" + regDay + ")";
        String regSecond = "(" + regDay + "/" + regMonth + "/" + regYear + ")";
        String regThird = "(" + regMonth + "-" + regDay + "-" + regYear + ")";
        Matcher matcher;
        matcher = Pattern.compile(regFirst + "|" + regSecond + "|" + regSecond).matcher(fileInString);
        while (matcher.find()) {
            dates.add(matcher.group());
        }
        writeFile(outputPath, "", false);
        for (String date : dates) {
            if (date.matches(regFirst)) {
                matcher = Pattern.compile(regFirst).matcher(date);
                if (matcher.find()) {
                    writeFile(outputPath, matcher.group(2) + matcher.group(3) + matcher.group(4), true);
                }
            }
            if (date.matches(regSecond)) {
                matcher = Pattern.compile(regSecond).matcher(date);
                if (matcher.find()) {
                    writeFile(outputPath, matcher.group(4) + matcher.group(3) + matcher.group(2), true);
                }
            }
            if (date.matches(regThird)) {
                matcher = Pattern.compile(regThird).matcher(date);
                if (matcher.find()) {
                    writeFile(outputPath, matcher.group(4) + matcher.group(2) + matcher.group(3), true);
                }
            }
            writeFile(outputPath, "\n", true);
        }
        return true;
    }

    /**
     * Дан список имен. Найти первое уникальное имя. Допустимая временная сложность - O(n)
     * при условии, что доступ к элементу списка по индексу - O(1).
     */
    public static void secondTask(String inputPath, String outputPath) {
        List<String> names = new ArrayList<>();
        String fileInString = readFile(inputPath);
        Pattern pattern = Pattern.compile("[A-Za-zА-Яа-я]+");
        Matcher matcher = pattern.matcher(fileInString);
        while (matcher.find()) {
            names.add(matcher.group());
        }
        for (String name : names) {
            if (Collections.frequency(names, name) == 1) {
                writeFile(outputPath, name, false);
                return;
            }
        }
    }

    /**
     * 3. Дан список городов. Каждый путь между городами имеет цену (целое положительное число).
     * Задача - найти самый выгодный путь между двумя городами. Максимально возможная цена пути - 200000.
     * <p>
     * n [количество городов <= 10000]
     * NAME [имя города]
     * p [количество соседей города NAME]
     * nr cost [nr - индекс соседа NAME (начиная с 1)]
     * [cost - стоимость пути]
     * r [количество путей, которые надо найти <= 100]
     * NAME1 NAME2 [NAME1 - начало пути, NAME2 - конец пути]
     */
    public static void thirdTask(String inputPath, String outputPath) {
        Map<String, Integer> cityId = new TreeMap<>();
        int[][] matrix;
        int countId = 0;
        int n;
        int p;
        int r;
        Matcher matcher;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputPath)));
            if (!((n = Integer.parseInt(reader.readLine())) <= 10_000)) {
                System.out.println("Error: количество городов > 10000");
                writeFile(outputPath, "Error: количество городов > 10000", false);
                return;
            }
            matrix = new int[n + 1][n + 1];
            for (int i = 0; i < n; i++) {
                cityId.put(reader.readLine(), ++countId);
                p = Integer.parseInt(reader.readLine());
                for (int j = 0; j < p; j++) {
                    matcher = Pattern.compile("\\s*(\\d+)\\s*(\\d+)\\s*").matcher(reader.readLine());
                    if (matcher.find()) {
                        matrix[countId][Integer.parseInt(matcher.group(1))] = Integer.parseInt(matcher.group(2));
                    }
                }
            }
            r = Integer.parseInt(reader.readLine());
            if (!(r <= 100)) {
                System.out.println("Error: количество путей, которые надо найти > 100");
                writeFile(outputPath, "Error: количество путей, которые надо найти > 100", false);
                return;
            }
            writeFile(outputPath, "", false);
            for (int i = 0; i < r; i++) {
                matcher = Pattern.compile("\\s*(\\w+)\\s*(\\w+)\\s*").matcher(reader.readLine());
                if (matcher.find()) {
                    int lowCost = findLowCost(cityId.get(matcher.group(1)), cityId.get(matcher.group(2)), matrix);
                    if (lowCost < 20_000) {
                        writeFile(outputPath, lowCost + "\n", true);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error read file: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error read file: " + e.getMessage());
                }
            }
        }
    }

    public static int findLowCost(int start, int finish, int[][] matrix) {
        Queue<Integer> q = new PriorityQueue<>();
        int[] dist = new int[10000];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        q.add(start);
        while (!q.isEmpty()) {
            int vertex = q.poll();
            for (int i = 0; i < matrix[vertex].length; i++) {
                if (matrix[vertex][i] != 0 && matrix[vertex][i] + dist[vertex] < dist[i]) {
                    dist[i] = matrix[vertex][i] + dist[vertex];
                    q.add(i);
                }
            }
        }
        return dist[finish];
    }

    private static String readFile(String inputPath) {
        try {
            return Files.readString(Path.of(inputPath));
        } catch (IOException e) {
            System.out.println("Error read file: " + e.getMessage());
            return "";
        }
    }

    private static boolean writeFile(String outputPath, String outData, boolean appendFlag) {
        try {
            if (appendFlag) {
                Files.writeString(Path.of(outputPath), outData, StandardOpenOption.APPEND);
            } else {
                Files.writeString(Path.of(outputPath), outData);
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error write file: " + e.getMessage());
            return false;
        }
    }
}