import dnfException.*;
import expression.*;
import parser.*;

public class Main {
    public static void main(String[] args) {
        Variable.initLetterVariables();
        Parser parser = new LogicExpressionParser();
        LogicExpression expression = parser.parse(args[0]);
        TruthTable truthTable = new TruthTable(expression);
        LogicExpression expressionDnf = truthTable.buildDnf();
        System.out.println(expressionDnf.toMiniString());
    }
}
