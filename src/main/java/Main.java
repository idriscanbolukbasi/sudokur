import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Difficulty difficulty = Difficulty.MEDIUM;
        System.out.println("Welcome to Sudokur" +
                "\nCommands are:" +
                "\n \"G\": Generates a 3x3 puzzle randomly, and prints out it on screen." +
                "\n \"S\": Solves the generated puzzle with a recursive brute force approach" +
                "\n \"D <E/M/H>\": Sets the difficulty of the puzzle will be generated default is \"D M\" which is medium difficulty." +
                "\nPlease type a command:");
        Scanner scanner = new Scanner(System.in);
        SudokuGenerator creator = null;
        SudokuSolver solver = null;

        while (true) {
            String input = scanner.nextLine();
            if (input.length() == 1) {
                switch (input) {
                    case "G":
                        if (creator != null) {
                            System.out.println("Please solve the generated puzzle by typing \"S\" first to generate a new one.");
                            continue;
                        }
                        creator = new SudokuGenerator(difficulty);
                        System.out.println("Board has been initialized. Please solve the current board with command \"S\"");
                        printBoard(creator.getBoard());
                        solver = null;
                        break;
                    case "S":
                        if (creator == null) {
                            System.out.println("Please generate a sudoku puzzle by typing \"G\" first to solve one.");
                            continue;
                        }
                        if (solver != null) {
                            System.out.println("The generated puzzle has been already solved. Please generate a new one first to solve it.");
                            continue;
                        }
                        solver = new SudokuSolver(creator.getBoard());
                        printBoard(solver.getBoard());
                        creator = null;
                        break;
                    default:
                        System.out.println("Given command is not suitable you can only use 2 commands with one character: \"G\" and \"S\"");
                        break;
                }
            } else {
                String[] arguments = input.split(" ");
                if (arguments[0].equals("D")) {
                    switch (arguments[1]) {
                        case "E":
                            difficulty = Difficulty.EASY;
                            System.out.println("The difficulty is now set as \"EASY\". Generate a new puzzle to solve it.");
                            break;
                        case "M":
                            difficulty = Difficulty.MEDIUM;
                            System.out.println("The difficulty is now set as \"MEDIUM\". Generate a new puzzle to solve it.");
                            break;
                        case "H":
                            difficulty = Difficulty.HARD;
                            System.out.println("The difficulty is now set as \"HARD\". Generate a new puzzle to solve it.");
                            break;
                        default:
                            System.out.println("Given command is not suitable for difficulty command. Please check and re-type your command, i.e., \"D E\"");
                    }
                }
            }
        }
    }

    public static void printBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (i % 3 == 0) {
                if (i == 0) System.out.println("╔═══╤═══╤═══╦═══╤═══╤═══╦═══╤═══╤═══╗");
                else System.out.println("\n╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣");
            } else System.out.println("\n╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
            for (int j = 0; j < board[i].length; j++) {
                String value = board[i][j] == null ? " " : board[i][j];
                if (j == board[i].length - 1) System.out.print("| " + value + " ║");
                else if (j % 3 == 0) System.out.print("║ " + value + " ");
                else System.out.print("│ " + value + " ");
            }
        }
        System.out.println("\n╚═══╧═══╧═══╩═══╧═══╧═══╩═══╧═══╧═══╝");
    }
}
