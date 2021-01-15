package dnfException;

public class EmptyParenthesisException extends ParseExpressionException{
    public EmptyParenthesisException() {
        super("EmptyParenthesis");
    }
}
