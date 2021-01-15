package dnfException;

public class EmptyExpressionException extends ParseExpressionException{
    public EmptyExpressionException() {
        super("EmptyExpression");
    }
}
