import java.util.*;

public class SudokuGenerator { // T = O(N^2) | instead of SudokuCreater (misspelled by the way) SudokuGenerator seems much more fine
    private final char DEFAULT = '\u0000'; // default value for blank character
    private final char[][] grid = { // we just got a 9x9 Sudoku grid which is fully solved since every Sudoku grid is unique the shuffled grid will be unique too
            {'B', 'G', 'I', '1', '2', 'M', 'C', 'N', '3'}, {'1', '2', 'M', 'N', 'C', '3', 'G', 'B', 'I'}, {'N', 'C', '3', 'B', 'G', 'I', '2', '1', 'M'},
            {'I', 'N', 'G', 'M', 'B', '2', '1', '3', 'C'}, {'3', '1', 'C', 'I', 'N', 'G', 'B', 'M', '2'}, {'M', 'B', '2', '3', '1', 'C', 'N', 'I', 'G'},
            {'2', 'I', 'B', 'C', 'M', '1', '3', 'G', 'N'}, {'G', '3', 'N', '2', 'I', 'B', 'M', 'C', '1'}, {'C', 'M', '1', 'G', '3', 'N', 'I', '2', 'B'}};
    // towards, we do not have to create an unique grid over an over again instead of we just create a new one by shuffling actually using the template
    // we can create as much grids as we can create from nothing with this shuffling stuff, so we don't have to create a grid from nothing
    private char[][] tempTable = new char[9][9]; // we got a temporary table to store row data of the template in order to shuffle rows
    private Random random = new Random(); // of course we will need random values to shuffle every row and column separately

    SudokuGenerator(Difficulty difficulty) { // we'll need difficulty in the argument of default constructor to hide cells accordingly
        for (int i = 0; i < 500 + random.nextInt(500); i++) shuffleRows(); // we'll shuffle rows 500-999 times
        for (int j = 0; j < 500 + random.nextInt(500); j++) shuffleColumns(); // we'll shuffle columns 500-999 times
        for (int k = 0; k < 500 + random.nextInt(500); k++) shuffleValues(); // we'll shuffle values 500-999 times
        int randomizeFactor = random.nextInt(difficulty.getMaximum() - difficulty.getMinimum()); // in case of getting a value between maximum and minimum
        hideCells(difficulty.getMinimum() + randomizeFactor); // we can just use this method
        Main.printGrid(grid); // and finally we're ready to print the grid
    }

    private void shuffleRows() { // T = O(1) | We're shuffling rows of the grid
        int firstRowOfGrids = random.nextInt(3) * 3; // we're getting first cell of the one of mini-grids which have 9 cells i.e., 0, 3, 6
        int randomGridRow = random.nextInt(3); // since every single mini-grid has 3x3 cells we can just simply shuffle rows by getting 0, 1, or 2 indexes
        tempTable[firstRowOfGrids] = grid[firstRowOfGrids + randomGridRow];
        grid[firstRowOfGrids + randomGridRow] = grid[firstRowOfGrids];
        grid[firstRowOfGrids] = tempTable[firstRowOfGrids];
    }

    private void shuffleValues() { // T = O(N^2) | We're shuffling values which are taken randomly
        char value1 = grid[0][random.nextInt(5)]; // 0, 1, 2, 3, 4 -> with this selection we will not encounter similar values
        char value2 = grid[0][5 + random.nextInt(4)]; // 5, 6, 7, 8 -> because only one value can exists once on one row of grid
        for (int row = 0; row < grid.length; row++) {
            int column1 = 0;
            int column2 = 0;
            for (int column = 0; column < grid[0].length; column++) {
                if (grid[row][column] == (value1)) column1 = column;
                if (grid[row][column] == (value2)) column2 = column;
            }
            grid[row][column1] = value2;
            grid[row][column2] = value1;
        }
    }

    private void shuffleColumns() { // T = O(N) | We're shuffling columns of the grid
        int firstRowOfColumns = random.nextInt(3) * 3; // same methodology in the shuffleRows() method runs fine on shuffleColumns() too
        int randomGridColumn = random.nextInt(3);
        for (int row = 0; row < 9; row++) {
            char temp = grid[row][firstRowOfColumns + randomGridColumn];
            grid[row][firstRowOfColumns + randomGridColumn] = grid[row][firstRowOfColumns];
            grid[row][firstRowOfColumns] = temp;
        }
    }

    public char[][] getGrid() { // to send the ready for solve puzzle to SudokuSolver in Main
        return grid;
    }

    private void hideCells(int threshold) { // we must hide cells to make a puzzle according to difficulty given in the default constructor
        int cellNumberToHide = 81 - threshold;
        int hidedCells = 0;
        while (hidedCells < cellNumberToHide) {
            int row = random.nextInt(9);
            int column = random.nextInt(9);
            if (grid[row][column] != DEFAULT) {
                grid[row][column] = DEFAULT;
                hidedCells++;
            }
        }
    }
}
