import Entry.Entry;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class StringMap {
    TreeMap<Integer, ArrayList<String>> map;
    public StringMap() {
        map = new TreeMap<Integer, ArrayList<String>>();
    }

    public void apply(Entry entry) {
        switch (entry.getType()) {
            case ADD -> add(entry.getIntArg(), entry.getStringArg());
            case REMOVE -> remove(entry.getIntArg());
            case PRINT -> print(entry.getStringArg());
        }
    }

    private void add(int index, String string) {
        ArrayList<String> array = map.get(index);
        if (array == null) {
            array = new ArrayList<String>();
            map.put(index, array);
        }
        array.add(string);
    }

    private void remove(int index) {
        map.remove(index);
    }

    private void print(String file) {
        try (PrintWriter out = new PrintWriter(file)) {
            for (Map.Entry<Integer, ArrayList<String>> entry : map.entrySet()) {
                for (String string : entry.getValue()) {
                    out.print(entry.getKey());
                    out.print(' ');
                    out.println(string);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file" + file);
            e.printStackTrace();
        }
    }
}
