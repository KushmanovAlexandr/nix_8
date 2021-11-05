package ua.com.alevel;

import ua.com.alevel.levelone.AreaOfTriangle;
import ua.com.alevel.levelone.ArrayOfNumbers;
import ua.com.alevel.levelone.KnightsMove;
import ua.com.alevel.leveltwo.BinaryTreeDepth;
import ua.com.alevel.leveltwo.StringCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {

    private void previewMainMenu() {
        System.out.println("=========================================");
        System.out.println("If you want first level please enter 1");
        System.out.println("If you want second level please enter 2");
        System.out.println("If you want third level please enter 3");
        System.out.println("If you want exit please enter 0");
        System.out.println("=========================================");
        System.out.print("Make your choice: ");
    }

    private void previewLevelOneMenu() {
        System.out.println("=========================================");
        System.out.println("If you want run 1.AreaOfTriangle please enter 1");
        System.out.println("If you want run 2.KnightsMove enter 2");
        System.out.println("If you want run 3.ArrayOfNumbers please enter 3");
        System.out.println("If you want return to the previous menu enter 4");
        System.out.println("If you want exit please enter 0");
        System.out.println("=========================================");
        System.out.print("Make your choice: ");
    }

    private void previewLevelTwoMenu() {
        System.out.println("=========================================");
        System.out.println("If you want run StringCheck please enter 1");
        System.out.println("If you want run BinaryTreeDepth please enter 2");
        System.out.println("If you want return to the previous menu enter 4");
        System.out.println("If you want exit please enter 0");
        System.out.println("=========================================");
        System.out.print("Make your choice: ");
    }

    private void previewLevelThreeMenu() {
        System.out.println("=========================================");
        System.out.println("If you want first level please enter 1");
        System.out.println("If you want return to the previous menu enter 4");
        System.out.println("If you want exit please enter 0");
        System.out.println("=========================================");
        System.out.print("Make your choice: ");
    }

    private void incorectPreview() {
        System.out.print("Incorrect value, please enter correct: ");
    }


    public void runMainMenu() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String choice;
            previewMainMenu();
            while ((choice = reader.readLine()) != null && choice.matches("\\d")) {
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        levelOneMenu(reader);
                        previewMainMenu();
                    }
                    case 2 -> {
                        levelTwoMenu(reader);
                        previewMainMenu();
                    }
                    case 3 -> {
                        levelThreeMenu(reader);
                        previewMainMenu();
                    }
                    case 0 -> {
                        System.out.println("\n *** Exit ***");
                        System.exit(0);
                    }
                    default -> incorectPreview();
                }
            }
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    private void levelOneMenu(BufferedReader reader) throws IOException {
        String choice;
        previewLevelOneMenu();
        while ((choice = reader.readLine()) != null && choice.matches("\\d")) {
            switch (Integer.parseInt(choice)) {
                case 1 -> {
                    new AreaOfTriangle().areaСalculation(reader);
                    previewLevelOneMenu();
                }
                case 2 -> {
                    new KnightsMove().runNextPosition(reader);
                    previewLevelOneMenu();
                }
                case 3 -> {
                    new ArrayOfNumbers().readLineParser(reader);
                    previewLevelOneMenu();
                }
                case 4 -> {
                    return;
                }
                case 0 -> {
                    System.out.println("\n *** Exit ***");
                    System.exit(0);
                }
                default -> incorectPreview();
            }
        }
    }

    private void levelTwoMenu(BufferedReader reader) throws IOException {
        String choice;
        previewLevelTwoMenu();
        while ((choice = reader.readLine()) != null && choice.matches("\\d")) {
            switch (Integer.parseInt(choice)) {
                case 1 -> {
                    new StringCheck().validate(reader);
                    previewLevelTwoMenu();
                }
                case 2 -> {
                    new BinaryTreeDepth();
                    previewLevelTwoMenu();
                }
                case 4 -> {
                    return;
                }
                case 0 -> {
                    System.out.println("\n *** Exit ***");
                    System.exit(0);
                }
                default -> incorectPreview();
            }
        }
    }

    private void levelThreeMenu(BufferedReader reader) throws IOException {
        String choice;
        previewLevelThreeMenu();
        while ((choice = reader.readLine()) != null && choice.matches("\\d")) {
            switch (Integer.parseInt(choice)) {
                case 1 -> {
                    System.out.println();
                    previewLevelThreeMenu();
                }
                case 4 -> {
                    return;
                }
                case 0 -> {
                    System.out.println("\n *** Exit ***");
                    System.exit(0);
                }
                default -> incorectPreview();
            }
        }
    }

}
