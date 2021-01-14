package parser;

import Entry.*;
import exceptions.ParseException;

import static Entry.EntryType.*;

import java.io.BufferedReader;
import java.io.IOException;

public class EntryParser {
    BufferedReader in;
    public boolean eof = false;
    public EntryParser(BufferedReader in) {
        this.in = in;
    }

    public Entry parseEntry() throws IOException {
        String line = in.readLine();
        if (line == null) {
            eof = true;
            return new Entry();
        }
        try {
            return new Parser(new StringSource(line)).parseEntry();
        } catch (ParseException e) {
            System.out.println("Could not parse the line:\n" + line);
            throw e;
        }
    }

    private static class Parser extends BaseParser{
        public Parser(CharSource source) {
            super(source);
            nextChar();
        }

        public Entry parseEntry() {
            EntryType type;
            if (test('a')) {
                expect("dd");
                type = ADD;
            } else if (test('r')) {
                expect("emove");
                type = REMOVE;
            } else if (test('p')) {
                expect("rint");
                type = PRINT;
            } else {
                throw error("Unknown command");
            }
            skipWhitespace();

            int intArg = -1;
            String stringArg = "";
            if (type == ADD || type == REMOVE) {
                intArg = parseIndex();
                if (intArg > 1_000_000_000 || intArg < -1_000_000_000) {
                    throw error("AbsoluteValue of index should not be greater than 1 000 000 000");
                }
            }
            skipWhitespace();
            if (type == ADD || type == PRINT) {
                stringArg = readString();
            }
            return new Entry(type, intArg, stringArg);
        }

        private String readString() {
            final StringBuilder sb = new StringBuilder();
            while (!eof() && ch != ' ') {
                sb.append(ch);
                nextChar();
            }
            return sb.toString();
        }

        private int parseIndex() {
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

        private void copyInteger(final StringBuilder sb) {
            if (test('-')) {
                sb.append('-');
            }
            if (test('0')) {
                sb.append('0');
            } else if (between('1', '9')) {
                copyDigits(sb);
            } else {
                throw error("Integer expected");
            }
        }

        private void copyDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(ch);
                nextChar();
            }
        }
    }
}
