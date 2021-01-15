package dnfException;

public class UnsupportedOperatorException extends ParseExpressionException {
    public UnsupportedOperatorException() {
        super("UnsupportedCharacter");
    }
}
