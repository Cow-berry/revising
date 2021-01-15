import expression.BooleanConst;
import expression.LogicExpression;
import expression.Operand;
import expression.Variable;

public class TruthTable {
    public LogicExpression expression;
    boolean[] table;

    public TruthTable(LogicExpression expression) {
        this.expression = expression;
        buildTable();
    }

    private void buildTable() {
        int count = Variable.countUsed();
        table = new boolean[1<<count];
        for (int arg = 0; arg < (1<<count); arg++) {
            int shift = 0;
            for (int i = 0; i < 26; i++) {
                if (Variable.used[i]) {
                    Variable.letterVariables[i].setValue((arg & (1<<(i-shift))) > 0);
                } else {
                    shift++;
                }
            }
            table[arg] = expression.evaluate();
        }
    }

    public LogicExpression buildDnf() {
        int count = Variable.countUsed();
        Operand dnf = null;
        for (int arg = 0; arg < (1<<count); arg++) {
            if (table[arg]) {
                Operand conjunction = null;
                int shift = 0;
                for (int i = 0; i < 26; i++) {
                    if (Variable.used[i]) {
                        Operand variable = Variable.letterVariables[i];
                        if ((arg & (1<<(i-shift))) == 0) {
                            variable = new expression.Not(variable);
                        }
                        if (conjunction == null) {
                            conjunction = variable;
                        } else {
                            conjunction = new expression.And(conjunction, variable);
                        }
                    } else {
                        shift++;
                    }
                }
                if (dnf == null) {
                    dnf = conjunction;
                } else {
                    dnf = new expression.Or(dnf, conjunction);
                }
            }
        }
        return dnf;
    }
}
