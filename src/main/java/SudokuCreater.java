import java.util.*;

public class SudokuCreater {
    private int[][] board = new int[9][9];
    private Random rand = new Random();
    private List<Integer> numbers = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

    SudokuCreater() {
        create();
    }

    private void create() {
        fillBox(0, 0);
    }

    public int[][] getBoard() {
        return board;
    }

    private void fillBox(int row, int col) {
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
                fillBox(tempRow, tempCol);
            }
        }

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

        for (int i = tempRow;i < 10 && i < tempRow + 3; i++) {
            for (int j = tempCol;j < 10 && j < tempCol + 3; j++) {
                if (value == board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
