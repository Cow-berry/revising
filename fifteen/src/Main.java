import exceptions.UnsolvablePuzzleException;
import game.Board;
import game.Position;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] field;
        try {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt(), m = sc.nextInt();
            Position.init(n, m);
            field = readField(sc, n, m);
            Board board = new Board(field, n, m);
            board.moveEmptySafely(new Position(1));
//            board.solve();
        } catch (NoSuchElementException e) {
            System.out.println("Enter sequence of numbers after specifying dimensions.\n" + e.getMessage());
        }
//        catch (UnsolvablePuzzleException e) {
//            System.out.println("Enter a solvable puzzle");
//        }
    }

    public static int[] readField(Scanner sc, int n, int m) throws NoSuchElementException {

        int[] field = new int[n*m];
        boolean[] check = new boolean[n*m];
        Arrays.fill(check, false);
        for (int i = 0; i < n * m; i++) {
            int number = sc.nextInt();
            if (check[number]) {
                throw new IllegalArgumentException("Numbers should go from 0 through n*m-1 not without any repeats. Repeated number: " + number);
            }
            check[number] = true;
            field[i] = number;
        }
        for (int i = 0; i < check.length; i++) {
            if (!check[i]) {
                throw new IllegalArgumentException("Not enough numbers. Number not present: " + i);
            }
        }
        return field;
    }

}
