import java.util.*;

public class ConnectFour {
    private static int rows = 6;
    private static int cols = 7;
    private static char board[][] = new char[rows][cols];

    public static void main(String[] args) {
        artTitle();
        int col = 0;
        int row = 0;
        boolean continueTurn, playerXTurn, continueGame;
        continueTurn = playerXTurn = continueGame = true;
        Scanner kb = new Scanner(System.in);
        createBoard(board);
        System.out.println("Player X, you're up first!");

        while (continueGame) {
            do {
                System.out.println("Where would you like to move? (Enter column #)");
                try {
                    col = kb.nextInt();
                    if (col > 6 || col < 0)
                        System.out.println("Column # out of range.");
                    else
                        continueTurn = false;

                    if (checkColFull(col, board) == true) {
                        System.out.println("That column is full! Please try a different one.");
                        continueTurn = true;
                    }
                } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please, make sure your input is a number between 0 - 6.");
                    continueTurn = true;
                }
                kb.nextLine();
            } while (continueTurn);

            if (playerXTurn) {
                row = playerXMove(col);
                if (checkForWin(board, row, col) == true) {
                    System.out.println("Player X Wins!");
                    displayBoard(board);
                    break;
                }
                else if (checkForDraw(board) == true){
                    System.out.println("Draw!");
                    displayBoard(board);
                    break;
                }
                displayBoard(board);
                playerXTurn = false;
                System.out.println("Player O, you're up next!");
            } else {
                row = playerOMove(col);
                if (checkForWin(board, row, col) == true) {
                    System.out.println("Player O Wins!");
                    displayBoard(board);
                    break;
                }
                else if (checkForDraw(board) == true){
                    System.out.println("Draw!");
                    displayBoard(board);
                    break;
                }
                displayBoard(board);
                playerXTurn = true;
                System.out.println("Player X, you're up next!");
            }
        }
        kb.close();
    }

    public static int playerXMove(int col) {
        int row = 0;
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][col] == ' ') {
                board[i][col] = 'X';
                row = i;
                break;
            }
        }
        return row;
    }

    public static int playerOMove(int col) {
        int row = 0;
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][col] == ' ') {
                board[i][col] = 'O';
                row = i;
                break;
            }
        }
        return row;
    }

    public static void displayBoard(char board[][]) {

        System.out.println("_____________________________________");
        System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
        for (int i = 0; i < board.length; i++) {
            System.out.print("|");
            for (int j = 0; j < board[0].length; j++) {
                System.out.print("| " + board[i][j] + " |");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
        System.out.println(" |  |                           |  | ");
        System.out.println("[____]                         [____]");
    }

    public static void createBoard(char board[][]) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static boolean checkColFull(int col, char board[][]) {
        return ((board[0][col] != ' ') ? true : false);
    }

    public static boolean checkVertical(char board[][], int col) {

        for (int i = 0; i < board.length - 3; i++) {
            int piece = board[i][col];
            if (piece != ' ') {
                if (piece == board[i + 1][col] &&
                        piece == board[i + 2][col] &&
                        piece == board[i + 3][col])
                    return true;
            }
        }
        return false;
    }

    public static boolean checkHorizontal(char board[][], int row) {

        for (int i = 0; i < board[0].length - 3; i++) {
            int piece = board[row][i];
            if (piece != ' ') {
                if (piece == board[row][i + 1] &&
                        piece == board[row][i + 2] &&
                        piece == board[row][i + 3])
                    return true;
            }
        }
        return false;
    }

    private static boolean checkDescDiagonal(char[][] board) {
        for (int i = 0; i < board.length - 3; i++) {
            for (int j = 0; j < board[i].length - 3; j++) {
                int piece = board[i][j];
                if (piece != ' ') {
                    if (piece == board[i + 1][j + 1] &&
                            piece == board[i + 2][j + 2] &&
                            piece == board[i + 3][j + 3])
                        return true;
                }
            }
        }
        return false;
    }

    private static boolean checkAscDiagonal(char[][] board) {
        for (int i = 0; i < board.length - 3; i++) {
            for (int j = 3; j < board[i].length; j++) {
                int piece = board[i][j];
                if (piece != ' ') {
                    if (piece == board[i + 1][j - 1] &&
                            piece == board[i + 2][j - 2] &&
                            piece == board[i + 3][j - 3])
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean checkForWin(char[][] board, int row, int col) {
        if (checkDescDiagonal(board) == true ||
                checkVertical(board, col) == true ||
                checkHorizontal(board, row) == true ||
                checkAscDiagonal(board) == true) {
            return true;
        }
        return false;
    }

    public static boolean checkForDraw(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    public static void artTitle(){
        System.out.println("                                                                              ____       ");
        System.out.println("    _______  _______   ___    __   ___    __   _______   _______ ________   /     |      ");
        System.out.println("   |   ___/ |  ___  | |   \\  |  | |   \\  |  | |   ____/ |   ___/ \\_    _/  /  /|  |   ");
        System.out.println("   |  |     | |   | | |    \\ |  | |    \\ |  | |  |___   |  |       |  |   /  /_|  |_");
        System.out.println("   |  |____ | |___| | |  |\\ \\|  | |  |\\ \\|  | |  |____  |  |____   |  |  /_____    _|     ");
        System.out.println("   |______/ |_______| |__| \\____| |__| \\____| |_______/ |______/   |__|        |__|");
        System.out.println("                       The classic vertical four-in-a-row game!");
        System.out.println();
    }
}