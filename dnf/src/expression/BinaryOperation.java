package expression;

public abstract class BinaryOperation implements Operand{
    protected Operand left, right;
    // :NOTE: Зависят от типа
    protected String sign;
    protected Priority priority;
    protected boolean associative;
    public boolean inverse;

    public BinaryOperation(Operand left, Operand right, String sign, Priority priority, boolean associative, boolean inverse){
        this.left = left;
        this.right = right;
        this.sign = sign;
        this.priority = priority;
        this.associative = associative;
        this.inverse = inverse;
    }

    @Override
    public String toString() {
        return "(" + left + " " + sign + " " + right + ")";
    }

    @Override
    public String toMiniString() {
        String leftMini = parenthesis(left, false);
        String rightMini = parenthesis(right, true);
        return String.format("%s %s %s", leftMini, sign, rightMini);
    }

    private String parenthesis(Operand operand, boolean right) {
        String miniOperand = operand.toMiniString();
        if (!(operand instanceof BinaryOperation)) {
            return miniOperand;
        }
        BinaryOperation operation = (BinaryOperation) operand;
        int comparison = operation.getPriority().compareTo(priority);
        if (comparison < 0 || (right && comparison == 0 && (!associative || operation.getInverse()))) {
            return "(" + miniOperand + ")";
        }
        return miniOperand;
    }

    @Override
    public boolean evaluate() {
        return operate(left.evaluate(), right.evaluate());
    }
    protected abstract boolean operate(boolean x, boolean y);


    public Priority getPriority() {
        return priority;
    }

    public boolean getInverse() {
        return inverse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryOperation that = (BinaryOperation) o;
        return this.left.equals(that.left) &&
                this.right.equals(that.right) &&
                this.sign.equals(that.sign) &&
                this.priority == that.priority;
    }

    @Override
    public int hashCode() {
        return ((sign.hashCode()*31 + left.hashCode())*31 + right.hashCode())*31 + priority.hashCode();
    }
}
