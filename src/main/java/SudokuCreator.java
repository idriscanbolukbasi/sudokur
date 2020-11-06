import java.util.*;

public class SudokuCreator {
    private int[][] board = new int[9][9];
    private Random rand = new Random();
    private List<Integer> numbers = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    private int cellNumberToHide;

    SudokuCreator(int cellNumberToHide) {
        this.cellNumberToHide = cellNumberToHide;
        while (!checkSudoku())
            fillCells(0, 0);
        hideCells();
    }


    public int[][] getBoard() {
        return board;
    }

    private void fillCells(int row, int col) {
        int tempRow = row;
        int tempCol = col;
        Collections.shuffle(numbers);

        for (int i = 0; i < numbers.size(); i++) {
            if (isLegal(row, col, numbers.get(i))) {
                board[row][col] = numbers.get(i);
                if (tempRow == 8) {
                    if (tempCol == 8) {
                        return;
                    } else {
                        tempRow = 0;
                        tempCol++;
                    }
                } else {
                    tempRow++;
                }
                fillCells(tempRow, tempCol);
            }
        }

    }

    private boolean checkSudoku() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    board = new int[9][9];
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isLegal(int row, int col, int value) {
        for (int i = 0; i < board.length; i++) {
            if (value == board[row][i]) {
                return false;
            }
        }
        for (int j = 0; j < board.length; j++) {
            if (value == board[j][col]) {
                return false;
            }
        }

        int tempRow = 0;
        int tempCol = 0;
        if (row > 2) {
            if (row > 5) {
                tempRow = 6;
            } else {
                tempRow = 3;
            }
        }
        if (col > 2) {
            if (col > 5) {
                tempCol = 6;
            } else {
                tempCol = 3;
            }
        }

        for (int i = tempRow; i < tempRow + 3; i++) {
            for (int j = tempCol; j < tempCol + 3; j++) {
                if (value == board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void hideCells() {
        for (int i = 0; i < cellNumberToHide; i++) {
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);

            board[row][col] = board[row][col] != 0 ? 0 : board[row][col];
        }
    }
}
