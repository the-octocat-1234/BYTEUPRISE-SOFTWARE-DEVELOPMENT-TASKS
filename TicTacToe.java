import java.util.Scanner;

public class TicTacToe {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = '-';

    private char[][] board;
    private char currentPlayer;
    private boolean gameEnded;

    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = 'X';
        gameEnded = false;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isMoveValid(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == EMPTY_CELL;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != EMPTY_CELL) {
                return true;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != EMPTY_CELL) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != EMPTY_CELL) ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != EMPTY_CELL)) {
            return true;
        }
        return false;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (!gameEnded) {
            System.out.println("Current board:");
            printBoard();
            System.out.println("Player " + currentPlayer + "'s turn. Enter row (1-3) and column (1-3) separated by space:");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;

            if (isMoveValid(row, col)) {
                board[row][col] = currentPlayer;
                if (checkWin()) {
                    gameEnded = true;
                    System.out.println("Player " + currentPlayer + " wins!");
                } else if (isBoardFull()) {
                    gameEnded = true;
                    System.out.println("It's a draw!");
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        scanner.close();
        System.out.println("Final board:");
        printBoard();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}
