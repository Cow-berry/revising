package dnfException;

public class MissingParathesisException extends ParseExpressionException{
    public MissingParathesisException() {
        super("MissingParenthesis");
    }
}
