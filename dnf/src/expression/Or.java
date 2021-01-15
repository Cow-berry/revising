package expression;

public class Or extends BinaryOperation{

    public Or(Operand left, Operand right) {
        super(left, right, "|", Priority.OR, true, false);
    }

    @Override
    protected boolean operate(boolean x, boolean y) {
        return x | y;
    }
}
