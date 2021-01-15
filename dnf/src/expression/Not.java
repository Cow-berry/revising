package expression;

public class Not extends UnaryOperation {
    public Not(Operand operand) {
        super(operand, "~");
    }

    @Override
    protected boolean operate(boolean expression) {
        return !expression;
    }
}