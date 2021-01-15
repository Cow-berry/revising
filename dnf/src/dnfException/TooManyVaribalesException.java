package dnfException;

public class TooManyVaribalesException extends ParseExpressionException{
    public TooManyVaribalesException() {
        super("TooManyVariables");
    }
}
