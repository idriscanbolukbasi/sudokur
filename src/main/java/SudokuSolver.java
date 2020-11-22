import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SudokuSolver { // T = O(N^3) | Class that contains methods to solve the given sudoku grid
    private final char DEFAULT = '\u0000'; // default value for blank character
    List<Character> values = new ArrayList<>(Arrays.asList('C', 'N', 'G', 'B', 'I', 'M', '2', '1', '3')); // values that we use in sudoku
    private char[][] grid; // we'll use this grid variable to save fully-solved grid

    SudokuSolver(char[][] grid) { // of course we'll need the grid that has to be solved as an argument
        if (solve(grid, 0, 0))
            Main.printGrid(this.grid); // if solve process is true, than we have to print the fully-solved grid
    }

    private boolean solve(char[][] grid, int row, int col) { // T = O(2^N) | recursive function that solves the grid according to given row and column value as arguments
        if (row == grid.length - 1 && col == grid.length) { // this seems like an error but it's not, since we'll increasing every column value to get next element,
            this.grid = grid; // when the solve method is done the row will become 8 (for 9x9 grid) and column will become 9 due to that situation
            return true; // and if they are become as 8 and 9 that indicates the progress is done and sudoku is solved
        }

        if (col == grid.length) { // if column = 9 (for 9x9) we don't have such an index in grid, so we have to
            row++; // increase row and
            col = 0; // reset column to 0 to move on
        }

        if (grid[row][col] != DEFAULT)
            return solve(grid, row, col + 1); // and of course if the cell in present is not blank, we have to exceed to the next

        for (int index = 0; index < 9; index++) { // T = O(N^3) |  we'll use brute force approach means we'll pick every single one value and try it for the cell in present
            char value = values.get(index); // we're getting one value
            if (isAppropriate(grid, row, col, value)) { // T = O(N^2) | we're controlling is this value appropriate for this cell or not
                grid[row][col] = value; // is it's suitable then we'll putting the value into this cell
                if (solve(grid, row, col + 1))
                    return true; // and we'll continuing to the next value till the grid is fully solved
            } // if it's not true for all values that we tried before it'll just pass the return statement
            grid[row][col] = DEFAULT; // and we'll set the given cell to default
        }
        return false; // and then return false to backtrack
    }

    private boolean isAppropriate(char[][] grid, int row, int col, char value) { // T = O(N^2)
        for (int i = 0; i < grid.length; i++) { // we're checking the every column and row for the value if there is a match of given value
            if (i != col) if (value == grid[row][i]) return false;
            if (i != row) if (value == grid[i][col]) return false;
        } // T = O(N)

        int gridRow = (row / 3) * 3; // we have get row and column indexes of mini-grid which has value that has row and column given as arguments
        int gridColumn = (col / 3) * 3;

        for (int r = 0; r < 3; r++) { // T = O(N^2)
            for (int c = 0; c < 3; c++) {
                if (value == grid[gridRow + r][gridColumn + c])
                    return false; // and we're checking the grids if there is a match of given value
            }
        }
        return true; // if there is not any match of value then that means value is suitable for the cell given
    }
}