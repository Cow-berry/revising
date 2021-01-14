package entry;

public class Entry {
    private final EntryType type;
    private final int intArg;
    private final String stringArg;
    public Entry(EntryType type, int intArg, String stringArg) {
        this.type = type;
        this.intArg = intArg;
        this.stringArg = stringArg;
    }

    public Entry(EntryType type, int intArg) {
        this(type, intArg, "");
    }

    public Entry(EntryType type, String stringArg) {
        this(type, 0, stringArg);
    }

    public Entry() {
        this(EntryType.EMPTY, 0, "");
    }

    public EntryType getType() {
        return type;
    }

    public int getIntArg() {
        return intArg;
    }

    public String getStringArg() {
        return stringArg;
    }
}
