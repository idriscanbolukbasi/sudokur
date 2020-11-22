import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    static final char DEFAULT = '\u0000'; // default value for characters that used in printing the grid
    static Difficulty difficulty = Difficulty.MEDIUM; // default difficulty

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8")); // to avoid platform (OS) dependent outputs like ??????
        System.out.println("Welcome to Sudokur" +
                "\nCommands are:" +
                "\n \"G\": Generates a 3x3 puzzle randomly, and prints out it on screen." +
                "\n \"S\": Solves the generated puzzle with a recursive brute force approach" +
                "\n \"D <E/M/H>\": Sets the difficulty of the puzzle will be generated, default is \"D E\" which is easy difficulty." +
                "\nYou can only use one command per line, please type a command:");
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
                        solver = new SudokuSolver(creator.getGrid());
                        creator = null;
                        break;
                    default:
                        System.out.println("Given command is not suitable you can only use 2 commands with one character: \"G\" and \"S\"" +
                                "\nOr you can set the difficulty to medium as an example: \"D M\"");
                        break;
                }
            } else {
                String[] arguments = input.split(" ");
                if (arguments.length == 0) {
                    System.out.println("Given command is not suitable, please check and re-type your command.");
                    continue;
                }
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
                            System.out.println("Given second argument is not suitable for difficulty command. Please check and re-type your command, i.e., \"D E\" \"D M\" \"D H\"");
                    }
                }
                System.out.println("Given command is not suitable, please check and re-type your command.");
            }
        }
    }

    public static void printGrid(char[][] grid) { // we just try to print sudoku grid as good as it could be
        for (int i = 0; i < grid.length; i++) {
            if (i % 3 == 0) {
                if (i == 0) System.out.println("╔═══╤═══╤═══╦═══╤═══╤═══╦═══╤═══╤═══╗");
                else System.out.println("\n╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣");
            } else System.out.println("\n╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
            for (int j = 0; j < grid[i].length; j++) {
                char value = grid[i][j];
                if (j == grid[i].length - 1)
                    System.out.print(value == DEFAULT ? "| " + " " + " ║" : "| " + value + " ║");
                else if (j % 3 == 0) System.out.print(value == DEFAULT ? "║ " + " " + " " : "║ " + value + " ");
                else System.out.print(value == DEFAULT ? "│ " + " " + " " : "│ " + value + " ");
            }
        }
        System.out.println("\n╚═══╧═══╧═══╩═══╧═══╧═══╩═══╧═══╧═══╝");
    }
}
