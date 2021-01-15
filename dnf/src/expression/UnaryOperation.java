package expression;

import java.util.Objects;

public abstract class UnaryOperation implements Operand{
    protected Operand operand;
    protected String name;

    public UnaryOperation(Operand operand, String name) {
        this.operand = operand;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "(" + operand + ")";
    }

    @Override
    public String toMiniString() {
        if (operand instanceof BinaryOperation || operand instanceof UnaryOperation) {
            return toString();
        }
        return name + " " + operand;
    }

    @Override
    public boolean evaluate() {
        return operate(operand.evaluate());
    }

    protected abstract boolean operate(boolean expression);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnaryOperation that = (UnaryOperation) o;
        return operand.equals(that.operand) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand, name);
    }
}
