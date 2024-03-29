package ua.com.alevel.sumofstringnumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class SumOfStringNumbers {


    public void run(BufferedReader reader) throws IOException {
        System.out.println("Task2.run");
        System.out.println("Enter your string:");
        Map<Character, Integer> charIn = new TreeMap<>();
        String line = reader.readLine();
        char[] charArray = line.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isLetter(charArray[i])) {
                if (!charIn.containsKey(charArray[i]))
                    charIn.put(charArray[i], 1);
                else
                    charIn.put(charArray[i], charIn.get(charArray[i]) + 1);
            }
        }
        int i = 1;
        System.out.println("The number of each character in the string:");
        for (Map.Entry<Character, Integer> charIntEntry : charIn.entrySet()) {
            System.out.printf("%d. %c - %d\n", i++, charIntEntry.getKey(), charIntEntry.getValue());
        }
        System.out.println("Select your event from 1 to 3 (0-exit):");

    }
}