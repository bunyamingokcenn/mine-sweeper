import java.util.Scanner;
import java.util.Random;

public class MineSweeper {
    private String[][] board;
    private String[][] mines;
    private int rows;
    private int cols;
    private int mineCount;
    private boolean isGameOver;

    public MineSweeper(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new String[rows][cols];
        this.mines = new String[rows][cols];
        this.mineCount = (rows * cols) / 4;
        this.isGameOver = false;
        initializeBoard();
        placeMines();
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = "-";
                mines[i][j] = "-";
            }
        }
    }

    private void placeMines() {
        Random random = new Random();
        int placedMines = 0;

        while (placedMines < mineCount) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);

            if (!mines[row][col].equals("*")) {
                mines[row][col] = "*";
                placedMines++;
            }
        }
    }

    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private boolean isMine(int row, int col) {
        return mines[row][col].equals("*");
    }

    private int countAdjacentMines(int row, int col) {
        int mineCounter = 0;
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];

            if (isValidCoordinate(newRow, newCol) && isMine(newRow, newCol)) {
                mineCounter++;
            }
        }
        return mineCounter;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver) {
            printBoard();
            System.out.print("Satır giriniz: ");
            int row = scanner.nextInt();
            System.out.print("Sütun giriniz: ");
            int col = scanner.nextInt();

            if (!isValidCoordinate(row, col)) {
                System.out.println("Geçersiz koordinat. Lütfen tekrar deneyin.");
                continue;
            }

            if (!board[row][col].equals("-")) {
                System.out.println("Bu koordinat daha önce seçildi, başka bir koordinat girin.");
                continue;
            }

            if (isMine(row, col)) {
                isGameOver = true;
                System.out.println("Mayına bastınız! Oyun bitti.");
                printMines();
            } else {
                int adjacentMines = countAdjacentMines(row, col);
                board[row][col] = String.valueOf(adjacentMines);

                if (checkWin()) {
                    isGameOver = true;
                    System.out.println("Tebrikler! Tüm mayınları buldunuz, oyunu kazandınız!");
                    printMines();
                }
            }
        }
        scanner.close();
    }

    private boolean checkWin() {
        int openedCells = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!board[i][j].equals("-")) {
                    openedCells++;
                }
            }
        }
        return openedCells == (rows * cols - mineCount);
    }

    private void printBoard() {
        System.out.println("Oyun Durumu:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void printMines() {
        System.out.println("Mayınların Konumu:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(mines[i][j] + " ");
            }
            System.out.println();
        }
    }
}
