package exceptions;

public class UnsolvablePuzzleException extends Exception {
    public UnsolvablePuzzleException() {
        super("Puzzle from the argument is unsolvable.");
    }
}
