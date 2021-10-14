package ua.com.alevel.char_from_string;

import java.io.BufferedReader;
import java.io.IOException;

public class CharFromString {

    public void run(BufferedReader reader) throws IOException {
        System.out.println("Task1.run");
        System.out.println("Enter your string:");
        int sum = 0;
        String line = reader.readLine();
        char[] charArray = line.toCharArray();
//        System.out.println(Arrays.toString(charArray));
//        for (int i = 0; i < charArray.length; i++) {
//            System.out.print(Character.isDigit(charArray[i])+" ");
//        }
        for (int i = 0; i < charArray.length; i++) {
            if(Character.isDigit(charArray[i]))
                sum +=Character.getNumericValue(charArray[i]);
        }
        System.out.println("Sum of all digits in a string = " +sum);
        System.out.println("Select your event from 1 to 3 (0-exit):");
    }
}