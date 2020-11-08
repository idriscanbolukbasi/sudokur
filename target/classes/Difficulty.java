public enum Difficulty {
    EASY(23, 32),
    MEDIUM(32, 35),
    HARD(35, 40);

    private final int min;
    private final int max;

    Difficulty(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMinimum() {
        return min;
    }

    public int getMaximum() {
        return max;
    }
}