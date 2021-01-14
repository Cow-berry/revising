import static Entry.EntryType.*;
import Entry.*;
import parser.EntryParser;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StringMap map = new StringMap();
        File input = new File(args[0]);
        EntryParser in = new EntryParser(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        while (!in.eof) {
            Entry entry = in.parseEntry();
            if (entry.getType() == EMPTY) {
                break;
            }
            map.apply(entry);
        }
    }
}
