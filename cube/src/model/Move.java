package model;

public class Move {
    Colors face;
    boolean clockwise;
    boolean twice;

    public Move(Colors face, boolean clockwise, boolean twice) {
        this.face = face;
        this.clockwise = clockwise;
        this.twice = twice;
    }
}
