package expression;

public class And extends BinaryOperation{
    public And(Operand left, Operand right) {
        super(left, right, "&", Priority.AND, true, false);
    }

    @Override
    protected boolean operate(boolean x, boolean y) {
        return x & y;
    }
}
