package dnfException;

public class MissingOperatorException extends ParseExpressionException{
    public MissingOperatorException() {
        super("MissingOperator");
    }
}
