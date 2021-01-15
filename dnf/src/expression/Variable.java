package expression;


import java.util.Arrays;

public class Variable implements Operand{
    public static Variable[] letterVariables;
    public static boolean[] used;
    private final char name;
    private boolean value;
    public Variable(char name) {
        this.name = name;
    }
    public static void initLetterVariables() {
        letterVariables = new Variable[26];
        for (char letter = 'a'; letter <= 'z'; letter++) {
            letterVariables[letter-'a'] = new Variable(letter);
        }
        used = new boolean[26];
        Arrays.fill(used, false);
    }

    public boolean evaluate() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public static int countUsed() {
        int count = 0;
        for (boolean present : Variable.used) {
            count += present ? 1 : 0;
        }
        return count;
    }


    public String toMiniString() {
        return toString();
    }

    public String toString() {
        return Character.toString(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable that = (Variable) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        return name;
    }
}
