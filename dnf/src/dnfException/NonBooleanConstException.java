package dnfException;

public class NonBooleanConstException extends ParseExpressionException{
    public NonBooleanConstException() {
        super("NonBooleanConst");
    }
}
