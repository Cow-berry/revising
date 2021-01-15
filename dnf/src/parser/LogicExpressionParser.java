package parser;

import dnfException.*;
import expression.*;

public class LogicExpressionParser implements Parser {
    public LogicExpression parse(String source) {
        return parse(new StringSource(source));
    }

    public LogicExpression parse(CharSource source) {
        return new Parser(source).parseExpression();
    }

    private static class Parser extends BaseParser {
        public Parser(CharSource source) {
            super(source);
            nextChar();
        }

        public LogicExpression parseExpression() {
            final LogicExpression result = start();
            if (result == null) {
                throw new EmptyExpressionException();
            }
            if (ch == ')') {
                throw new MissingParathesisException();
            }
            if (Variable.countUsed() > 10) {
                throw new TooManyVaribalesException();
            }
            skipWhitespace();
            if (eof()) {
                return result;
            }
            throw error("End of Expression expected");
        }

        public Operand start() {
            return start(null, null);
        }

        public Operand start(Priority priority) {
            return start(null, priority);
        }

        public Operand start(Operand operand, Priority priority) {
            skipWhitespace();
            if (eof()) {
                return operand;
            }
            Operand newStartingPoint = null;
            if (Character.isDigit(ch)) {
                if (operand != null) {
                    throw new MissingOperatorException();
                }
                if (!between('0', '1')) {
                    throw new NonBooleanConstException();
                }
                newStartingPoint = new BooleanConst(ch == '1');
            } else if (between('a', 'z')) {
                newStartingPoint = Variable.letterVariables[ch-'a'];
                Variable.used[ch-'a'] = true;
                nextChar();
            } else if (operand == null && test('~')) {
                Operand argument = start(Priority.UNARY);
                if (argument == null) {
                    throw new MissingArgumentException();
                }
                newStartingPoint = new Not(argument);
            } else if (think("&|")) {
                if (operand == null) {
                    throw new MissingArgumentException();
                }
                Priority presentPriority = ch == '&' ? Priority.AND : Priority.OR;
                if (priorityCheck(priority, presentPriority)) {
                    char copiedSign = ch;
                    nextChar();
                    Operand secondArgument = getArgument(start(presentPriority));
                    newStartingPoint = switch (copiedSign) {
                        case '&' -> new And(operand, secondArgument);
                        case '|' -> new Or(operand, secondArgument);
                        default -> throw new IllegalStateException("Unexpected value: " + ch);
                    };
                }
            } else if (test('(')) {
                newStartingPoint = start();
                if (newStartingPoint == null) {
                    throw new EmptyParenthesisException();
                }
                if (ch != ')') {
                    throw new MissingParathesisException();
                }
                nextChar();
            } else if (!think(')')) {
                throw new UnsupportedOperationException();
            }

            if (newStartingPoint == null) {
                return operand;
            }
            return start(newStartingPoint, priority);
        }

        private Operand getArgument(Operand operand) {
            if (operand == null) {
                throw new MissingArgumentException();
            }
            return operand;
        }

        private boolean priorityCheck(Priority priority, Priority base) {
            return !(priority != null && priority.compareTo(base) >= 0);
        }

        private void skipWhitespace() {
            while (test(' ') || test('\r') || test('\n') || test('\t')) {
                // skip
            }
        }
    }
}
