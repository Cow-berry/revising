package expression;

public class BooleanConst implements Operand {
    private final boolean constant;

    public BooleanConst(boolean constant) {
        this.constant = constant;
    }

    public boolean evaluate() {
        return constant;
    }

    public String toMiniString() {
        return toString();
    }

    public String toString() {
        return constant ? "1" : "0";
    }
}
