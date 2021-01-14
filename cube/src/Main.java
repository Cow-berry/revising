import model.Colors;
import parser.MovesParser;
import parser.*;
import model.*;

public class Main {
    public static void main(String[] args) {
        long a = 0400000000000000000000L;
        Parser parser = new MovesParser();
        Move[] moves = parser.parse(args[0]);
        Cube cube = new Cube();
        Cube result = cube.apply(moves);
        System.out.println(result);
    }
}
