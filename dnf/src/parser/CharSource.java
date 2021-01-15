package parser;

import dnfException.ParseException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface CharSource {
    boolean hasNext();
    char next();
    void goBack(int length);
    ParseException error(final String message);
}
