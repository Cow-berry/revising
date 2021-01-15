package parser;

import dnfException.ParseExpressionException;
import expression.LogicExpression;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser {
    LogicExpression parse(String expression) throws ParseExpressionException;
}
