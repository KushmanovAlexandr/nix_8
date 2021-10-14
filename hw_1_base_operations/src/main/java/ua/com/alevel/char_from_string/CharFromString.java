package ua.com.alevel.char_from_string;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.util.Scanner;

public class CharFromString {

    public void run(BufferedReader reader) throws IOException {
        System.out.println("Task1.run");
        int sum = 0;
        Scanner sc=new Scanner(System.in);
        String line = sc.nextLine();
//        System.out.println(line);
        char[] charArray = line.toCharArray();
//        System.out.println(Arrays.toString(charArray));
//        for (int i = 0; i < charArray.length; i++) {
//            System.out.print(Character.isDigit(charArray[i])+" ");
//        }
        for (int i = 0; i < charArray.length; i++) {
            if(Character.isDigit(charArray[i]))
                sum +=Character.getNumericValue(charArray[i]);
        }
        System.out.println(sum);
    }
}