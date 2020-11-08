import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuSolver {
    private String[][] board;
    private int emptyCells = 0;

    SudokuSolver(String[][] board) {
        this.board = board;
        analyze();
        System.out.println(emptyCells);
        solve();
    }

    private void analyze() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null) emptyCells++;
            }
        }
    }

    private void solve() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null) if (checkUniqueness(i, j)) emptyCells--;
            }
        }
        if (emptyCells > 0) solve();
    }

    private boolean checkUniqueness(int row, int column) {
        List<String> values = new ArrayList<>(Arrays.asList("C", "N", "G", "B", "I", "M", "2", "1", "3"));
        for (int i = 0; i < 9; i++) {
            values.remove(board[row][i]);
            values.remove(board[i][column]);
        }
        int boxRow = (row / 3) * 3;
        int boxColumn = (column / 3) * 3;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                values.remove(board[boxRow + r][boxColumn + c]);
            }
        }

        if (values.size() == 1) {
            board[row][column] = values.get(0);
            return true;
        }
        return false;
    }

    public String[][] getBoard() {
        return board;
    }


}