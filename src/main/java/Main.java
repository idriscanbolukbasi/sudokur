public class Main {
    public static void main(String[] args) {
        SudokuCreater creater = new SudokuCreater();
        int[][] board = creater.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
