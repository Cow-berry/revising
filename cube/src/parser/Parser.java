package parser;

import exceptions.ParseException;
import model.Move;

public interface Parser {
    Move[] parse(String moves) throws ParseException;
}
