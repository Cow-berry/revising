package dnfException;

public class MissingArgumentException extends ParseExpressionException {
    public MissingArgumentException() {
        super("MissingArgument");
    }
}
