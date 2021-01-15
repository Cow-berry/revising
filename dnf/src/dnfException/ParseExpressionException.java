package dnfException;

public class ParseExpressionException extends RuntimeException{
    public ParseExpressionException() {
        super("ParseException");
    }

    public ParseExpressionException(String message) {
        super(message + "Exception");
    }
}
