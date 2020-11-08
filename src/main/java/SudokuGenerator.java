import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    private final String[][] template = { // We just got a 9x9 Sudoku grid which is fully solved since every Sudoku grid is unique the shuffled grid will be unique too.
            {"B", "G", "I", "1", "2", "M", "C", "N", "3"}, {"1", "2", "M", "N", "C", "3", "G", "B", "I"}, {"N", "C", "3", "B", "G", "I", "2", "1", "M"},
            {"I", "N", "G", "M", "B", "2", "1", "3", "C"}, {"3", "1", "C", "I", "N", "G", "B", "M", "2"}, {"M", "B", "2", "3", "1", "C", "N", "I", "G"},
            {"2", "I", "B", "C", "M", "1", "3", "G", "N"}, {"G", "3", "N", "2", "I", "B", "M", "C", "1"}, {"C", "M", "1", "G", "3", "N", "I", "2", "B"}};
    // Towards, we do not have to create an unique grid over an over again instead of we just create a new one by shuffling actually using the template.
    private String[][] tempTable = new String[9][9]; // We got a temporary table to store row data of the template in order to shuffle rows.
    private Random random = new Random(); // Of course we will need random values to shuffle every row and column separately.

    SudokuGenerator(Difficulty difficulty) {
        for (int i = 0; i < 500 + random.nextInt(500); i++) shuffleRows();
        for (int j = 0; j < 500 + random.nextInt(500); j++) shuffleColumns();
        int randomizeFactor = random.nextInt(difficulty.getMaximum() - difficulty.getMinimum());
        hideCells(difficulty.getMinimum() + randomizeFactor);
    }

    private void shuffleRows() {
        int firstRowOfGrids = random.nextInt(3) * 3;
        int randomGridRow = random.nextInt(3);
        tempTable[firstRowOfGrids] = template[firstRowOfGrids + randomGridRow];
        template[firstRowOfGrids + randomGridRow] = template[firstRowOfGrids];
        template[firstRowOfGrids] = tempTable[firstRowOfGrids];
    }

    private void shuffleColumns() {
        int firstRowOfColumns = random.nextInt(3) * 3;
        int randomGridColumn = random.nextInt(3);
        for (int row = 0; row < 9; row++) {
            String temp = template[row][firstRowOfColumns + randomGridColumn];
            template[row][firstRowOfColumns + randomGridColumn] = template[row][firstRowOfColumns];
            template[row][firstRowOfColumns] = temp;
        }
    }

    public String[][] getBoard() {
        return template;
    }

    private void hideCells(int threshold) {
        int cellNumberToHide = 81 - threshold;
        int hidedCells = 0;
        while (hidedCells < cellNumberToHide) {
            int row = random.nextInt(9);
            int column = random.nextInt(9);
            if (template[row][column] == null) continue;
                String temp = template[row][column];
                template[row][column] = null;
                if (checkUniqueness(row, column)) hidedCells++;
                else template[row][column] = temp;
        }
    }

    private boolean checkUniqueness(int row, int column) {
        List<String> values = new ArrayList<>(Arrays.asList("C", "N", "G", "B", "I", "M", "2", "1", "3"));
        for (int i = 0; i < 9; i++) {
            values.remove(template[row][i]);
            values.remove(template[i][column]);
        }
        int boxRow = (row / 3) * 3;
        int boxColumn = (column / 3) * 3;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                values.remove(template[boxRow + r][boxColumn + c]);
            }
        }

        return values.size() == 1;
    }
}
