// package com.tictactoe;

import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    static char[][] board;

    // Constructor to initialize the board
    public TicTacToe() {
        board = new char[3][3];
        initBoard();
    }

    // Initializing the board (Method)
    void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Displaying the board (Method)
    void displayBoard() {
        System.out.println("     0     1     2");
        System.out.println("  -------------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
            System.out.print("|  ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "  |  ");
            }
            System.out.println();
            System.out.println("  -------------------");
        }
    }

    // Placing the Mark in the board (Method)
    static void placeMark(int row, int col, char ch) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            board[row][col] = ch;
        } else {
            System.out.println("Invalid Position");
        }
    }

    // Checking Row Win Conditions (Method)
    static boolean rowWin() {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    // Checking Column Win Conditions (Method)
    static boolean colWin() {
        for (int j = 0; j < board.length; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    // Checking Diagonal Win Conditions (Method)
    static boolean diagonalWin() {
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2] || board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    // Checking whether the Match Is Draw (Method)
    static boolean isDraw() {
        for (int i = 0; i < TicTacToe.board.length; i++) {
            for (int j = 0; j < TicTacToe.board[i].length; j++) {
                if (TicTacToe.board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

// Parent Class that Holds the Structure
abstract class Player {
    String name;
    char mark;

    abstract void makeMove();

    boolean isValidMove(int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (TicTacToe.board[row][col] == ' ') {
                return true;
            }
        }
        return false;
    }
}

// Child Class that Extends the Properties of Parent Class
class HumanPlayer extends Player {

    public HumanPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    // Making the Moves (Method)
    void makeMove() {
        Scanner scan = new Scanner(System.in);
        int row;
        int col;
        do {
            System.out.println("Enter Row and Column: ");
            row = scan.nextInt();
            col = scan.nextInt();
        } while (!isValidMove(row, col));
        TicTacToe.placeMark(row, col, mark);
    }
}

// Child Class that Extends the Properties of Parent Class
class AIPlayer extends Player {

    public AIPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    // Making the Moves (Method)
    void makeMove() {
        Scanner scan = new Scanner(System.in);
        int row;
        int col;
        do {
            Random r = new Random();
            row = r.nextInt(3);
            col = r.nextInt(3);
        } while (!isValidMove(row, col));
        TicTacToe.placeMark(row, col, mark);
    }
}


// Main Class Begins
public class LaunchGame {
    // Main Method Begins
    public static void main(String[] args) {

        // Providing the Headline for Game
        System.out.println();
        System.out.println("      TIC TAC TOE");
        System.out.println("----------------------");

        // Creating Object for Scanner Class
        Scanner scan = new Scanner(System.in);

        // Asking User the Options and Storing the Option
        System.out.println("Select Option: \n 1. Multiplayer  \n 2. Play With AI");
        System.out.println("Option 1 or 2 : ");
        int option = scan.nextInt();

        // Creating Object for TicTacToe Class
        TicTacToe t = new TicTacToe();

        // Creating References of Parent Class
        Player p1;
        Player p2;

        // Verifying the Options
        if (option == 1) {
            System.out.println("Enter Player 1 Name: ");
            String name1 = scan.next();
            System.out.println("Enter Player 2 Name: ");
            String name2 = scan.next();
            p1 = new HumanPlayer(name1, 'X');
            p2 = new HumanPlayer(name2, 'O');
        } else {
            System.out.println("Enter Player Name: ");
            String name = scan.next();
            p1 = new HumanPlayer(name, 'X');
            p2 = new AIPlayer("AI", 'O');
        }

        // Creating Reference of Parent Class
        Player cp;

        // Assigning address of p1 to cp(Current Player)
        cp = p1;

        // Displaying The Board
        t.displayBoard();

        // Executing the loop until the loop breaks
        while (true) {
            System.out.println(cp.name + " Turn");
            cp.makeMove();
            t.displayBoard();
            if (TicTacToe.rowWin() || TicTacToe.colWin() || TicTacToe.diagonalWin()) {
                System.out.println(cp.name + " Won");
                break;
            } else if (TicTacToe.isDraw()) {
                System.out.println("It's Draw");
                break;
            } else {
                if (cp == p1) {
                    cp = p2;
                } else {
                    cp = p1;
                }
            }
        }

    }
}