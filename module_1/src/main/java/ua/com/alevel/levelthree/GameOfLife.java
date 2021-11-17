package ua.com.alevel.levelthree;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class GameOfLife {

    private boolean[][] board;
    private boolean[][] nextBoard;

    private void createBoard(int n, int m) {
        board = new boolean[m][n];
        fillBoard();
        showBoard();
    }

    private void fillBoard() {
        Random random = new Random();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = random.nextBoolean();
            }
        }
    }

    private void showBoard() {
        for (boolean[] booleans : board) {
            for (boolean aBoolean : booleans) {
                if (aBoolean)
                    System.out.print("@\t");
                else
                    System.out.print("В·\t");
            }
            System.out.println();
        }
        System.out.println("=========================================");
    }

    private void nextStep(int n, int m) {
        nextBoard = new boolean[m][n];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                nextBoard[i][j] = isAlive(i, j);
            }
        }
        board = nextBoard;
        showBoard();
    }

    private boolean isAlive(int x, int y) {
        int count = 0;
        int iTmp;
        int jTmp;
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                iTmp = i;
                jTmp = j;
                if (i < 0)
                    iTmp = board.length - 1;
                if (i > board.length - 1)
                    iTmp = 0;
                if (j < 0)
                    jTmp = board[iTmp].length - 1;
                if (j > board[iTmp].length - 1)
                    jTmp = 0;
                if (x == i && y == j)
                    continue;
                if (board[iTmp][jTmp])
                    count++;
            }
        }
        if (count < 2 || count > 3 && board[x][y]) {
            return false;
        } else if (count == 3 && !board[x][y]) {
            return true;
        }
        return board[x][y];
    }

    public void run(BufferedReader reader) throws IOException {
        while (true) {
            System.out.println("********** GameOfLife **********");
            System.out.println("Please enter size board");
            System.out.print("n: ");
            int n = inputTestParser(reader);
            System.out.print("m: ");
            int m = inputTestParser(reader);
            createBoard(n, m);
            nextStep(n, m);
            nextStep(n, m);
            nextStep(n, m);
            nextStep(n, m);
            nextStep(n, m);
            nextStep(n, m);
            System.out.println("=========================================");
            System.out.println("If you want try again please press Enter");
            System.out.println("If you want return please enter 0");
            System.out.print("Make your choice: ");
            if (reader.readLine().equals("0"))
                return;
        }
    }

    private int inputTestParser(BufferedReader reader) throws IOException {
        String choice;
        while ((choice = reader.readLine()) != null && !choice.matches("\\d+")) {
            System.out.print("Please input correct value: ");
        }
        return Integer.parseInt(choice);
    }
}
