package parser;

import model.Colors;
import model.Move;

import java.util.Arrays;

import static model.Colors.*;

public class MovesParser implements Parser {
    public Move[] parse(String source) {
        return parse(new StringSource(source));
    }

    public Move[] parse(CharSource source) {
        return new Parser(source).parseMoves();
    }

    private static class Parser extends BaseParser {
        public Parser(CharSource source) {
            super(source);
            nextChar();
        }

        public Move[] parseMoves() {
            Move[] result = new Move[1];
            int index = 0;
            while (!eof()) {
                if (index >= result.length) {
                    result = Arrays.copyOf(result, index*2);
                }
                result[index++] = parseMove();
                skipWhitespace();
            }
            if (result[0] == null) {
                throw error("Empty moves sequence");
            }
            return Arrays.copyOf(result, index);
        }

        public Move parseMove() {
            skipWhitespace();
            Colors face;
            boolean clockwise, twice;
            try {
                face = switch (ch) {
                    case 'R' -> O;
                    case 'L' -> R;
                    case 'F' -> B;
                    case 'B' -> G;
                    case 'U' -> W;
                    case 'D' -> Y;
                    default -> throw new IllegalStateException("Unexpected value: " + ch);
                };
            } catch (IllegalArgumentException e) {
                throw error("Couldn't find move's face literal");
            }
            nextChar();
            clockwise = !test('\'');
            int times = parseNumber();
            times = times % 4;
            twice = times == 2;
            clockwise = (times == 3) ^ clockwise;
            return new Move(face, clockwise, twice);
        }

        private int parseNumber() {
            final StringBuilder sb = new StringBuilder();
            copyInteger(sb);
            if (sb.length() == 0) {
                return 0;
            }
            try {
                return Integer.parseInt(sb.toString());
            } catch (NumberFormatException e) {
                throw error("Invalid number " + sb);
            }
        }

        private void copyDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(ch);
                nextChar();
            }
        }

        private void copyInteger(final StringBuilder sb) {
            if (test('-')) {
                sb.append('-');
            }
            if (test('0')) {
                sb.append('0');
            } else if (between('1', '9')) {
                copyDigits(sb);
            }
        }
    }
}
