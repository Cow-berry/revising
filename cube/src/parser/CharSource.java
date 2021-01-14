package parser;

import exceptions.ParseException;

public interface CharSource {
        boolean hasNext();
        char next();
        ParseException error(final String message);
}
