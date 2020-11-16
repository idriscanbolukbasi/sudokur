public enum Difficulty {
    EASY(35, 41), // Easy difficulty
    MEDIUM(28, 34), // Medium difficulty
    HARD(21, 27); // A puzzle with a unique solution must have at least 17 clues, and there is a solvable puzzle with at most 21 clues for every solved grid

    private final int min;
    private final int max;

    Difficulty(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMinimum() {
        return min;
    }

    public int getMaximum() { return max; }
}