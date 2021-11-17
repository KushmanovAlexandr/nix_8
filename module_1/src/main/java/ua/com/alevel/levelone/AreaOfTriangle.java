package ua.com.alevel.levelone;

import java.io.BufferedReader;
import java.io.IOException;

public class AreaOfTriangle {

    private double inputTestDoubleParser(BufferedReader reader) throws IOException {
        String choice;
        while ((choice = reader.readLine()) != null && !choice.matches("\\d+\\.?\\d*")) {
            System.out.print("Please input correct value (sample 1 or 1.0): ");
        }
        return Double.parseDouble(choice);
    }

    public void areaСalculation(BufferedReader reader) throws IOException {
        while (true) {
            System.out.println("********** AreaOfTriangle **********");
            System.out.println("Enter point A coordinates");
            System.out.print("x: ");
            double x1 = inputTestDoubleParser(reader);
            System.out.print("y: ");
            double y1 = inputTestDoubleParser(reader);
            System.out.println("Enter point B coordinates");
            System.out.print("x: ");
            double x2 = inputTestDoubleParser(reader);
            System.out.print("y: ");
            double y2 = inputTestDoubleParser(reader);
            System.out.println("Enter point C coordinates");
            System.out.print("x: ");
            double x3 = inputTestDoubleParser(reader);
            System.out.print("y: ");
            double y3 = inputTestDoubleParser(reader);
            double area = Math.abs(((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1)) / 2);
            System.out.println("Area of triangle = " + area);
            System.out.println("=========================================");
            System.out.println("If you want try again please press Enter");
            System.out.println("If you want return please enter 0");
            System.out.print("Make your choice: ");
            if (reader.readLine().equals("0"))
                return;
        }
    }
}