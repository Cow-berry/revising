package game;

public class Position {
    static int n, m;
    int[] coords;
    int number;

    public static void init(int n, int m) {
        Position.n = n;
        Position.m = m;
    }

    public Position(int row, int column) {
        this.number = row * m + column;
        this.coords = new int[]{row, column};
    }

    public Position(int number) {
        this.number = number;
        this.coords = new int[]{number / m, number % m};
    }

    public int getNumber() {
        return number;
    }

    public int[] getCoords() {
        return coords;
    }

    public int x() {
        return coords[1];
    }

    public int y() {
        return coords[0];
    }

    public void move(int dx, int dy) {
        this.coords[0] += dx;
        this.coords[1] += dy;
        this.number = this.coords[0]*m + this.coords[1];
    }
}
