package ua.com.alevel.levelone;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class KnightsMove {

    private static char[][] chessBoard;

    private void fillBoard() {
        for (char[] chars : chessBoard) {
            Arrays.fill(chars, '*');
        }
    }

    private void showBoard() {
        if (chessBoard == null) {
            chessBoard = new char[8][8];
            fillBoard();
        }
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                if (j == 0) {
                    System.out.print((8 - i) + "\t");
                }
                System.out.print(chessBoard[chessBoard.length - i - 1][j] + "\t");
            }
            System.out.println();
            if (i == chessBoard.length - 1)
                System.out.println(" \tA\tB\tC\tD\tE\tF\tG\tH");
        }
    }

    private int[] handlingMove(BufferedReader reader) throws IOException {
        char[] position;
        int[] a;
        System.out.print("Please enter cell: ");
        String s;
        while ((s = reader.readLine()) != null && !s.matches("[a-hA-H][1-8]")) {
            System.out.println("Incorrect value please enter cell: ");
        }
        position = s.toCharArray();
        a = new int[]{Integer.parseInt(String.valueOf(position[1])) - 1, Character.toLowerCase(position[0]) - 'a'};
        return a;
    }

    public void runNextPosition(BufferedReader reader) throws IOException {
        int x = 0;
        int y = 0;
        int nextX = 0;
        int nextY = 0;
        int count = 0;
        System.out.println("********** KnightsMove **********");
        while (true) {
            if (count == 0) {
                showBoard();
            }
            int[] position = handlingMove(reader);
            if (count == 0) {
                x = position[0];
                y = position[1];
                chessBoard[x][y] = 'K';
                showBoard();
                count++;
            } else {
                nextX = position[0];
                nextY = position[1];
                if (Math.abs(x - nextX) == 1 && Math.abs(y - nextY) == 2 || Math.abs(x - nextX) == 2 && Math.abs(y - nextY) == 1) {
                    chessBoard[x][y] = '0';
                    chessBoard[nextX][nextY] = 'K';
                    x = nextX;
                    y = nextY;
                    count++;
                    showBoard();
                } else {
                    System.out.print("Impossible to make a move ");
                }
            }
            if (count == 1)
                continue;
            x = y = nextX = nextY = count = 0;
            fillBoard();
            System.out.println("=========================================");
            System.out.println("If you want try again please press Enter");
            System.out.println("If you want return please enter 4");
            System.out.print("Make your choice: ");
            if (reader.readLine().equals("4"))
                return;
        }
    }
}