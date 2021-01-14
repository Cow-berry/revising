package parser;

import exceptions.*;

public interface CharSource {
        boolean hasNext();
        char next();
        ParseException error(final String message);
}
