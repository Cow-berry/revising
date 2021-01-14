package model;

public enum Colors {
    W, R, B, O, G, Y;

    public static final String ANSI_WHITE = "\u001B[40m";
    public static final String ANSI_RED = "\033[48;5;196m";
    public static final String ANSI_GREEN = "\033[48;5;46m";
    public static final String ANSI_YELLOW = "\033[48;5;226m";
    public static final String ANSI_BLUE = "\033[48;5;33m";
    public static final String ANSI_PURPLE = "\u001B[45m";
    public static final String ANSI_CYAN = "\u001B[46m";
    public static final String ANSI_BLACK = "\u001B[47m";
    public static final String ANSI_ORANGE = "\033[48;5;214m";

    @Override
    public String toString() {
        return switch (this) {
            case W -> ANSI_WHITE;
            case R -> ANSI_RED;
            case B -> ANSI_BLUE;
            case O -> ANSI_ORANGE;
            case G -> ANSI_GREEN;
            case Y -> ANSI_YELLOW;
        } + "   \u001B[0m";
    }
}
