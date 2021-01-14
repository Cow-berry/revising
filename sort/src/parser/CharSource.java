package parser;

import exceptions.ParseException;

import java.io.IOException;

public interface CharSource {
        boolean hasNext();
        char next();
        ParseException error(final String message);
}
