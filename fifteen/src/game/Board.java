package game;

import exceptions.UnsolvablePuzzleException;

import java.util.ArrayList;

public class Board {
    int n, m;
    int[] board;
    Position emptyPos;
    public Board(int[] board, int n, int m) {
        this.board = board;
        this.n = n;
        this.m = m;
        for (int i = 0; i < n * m; i++) {
            if (board[i] == 0) {
                emptyPos = new Position(i);
                break;
            }
        }
    }

    public void solve() throws UnsolvablePuzzleException {
        if (!solvable()) {
            throw new UnsolvablePuzzleException();
        }
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < (row < n-2 ? m : m-2) ; column++) {
                placeNumber(new Position(row, column));
            }
        }
        // достроить остальные части
    }

    public void placeNumber(Position position, int number) {
        moveEmptySafely();
    }

    public void placeNumber(Position position) {
        placeNumber(position, position.number);
    }

    public void moveEmptySafely(Position position) {
        while (emptyPos.x() != position.x()) {
            makeMove(emptyPos.x() < position.x() ? Move.Right : Move.Left);
        }
        while (emptyPos.y() != position.y()) {
            makeMove(emptyPos.y() < position.y() ? Move.Down : Move.Up);
        }
    }

    public void makeMoves(Move[] moves, int[] times) {
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < times[i]; j++) {
                makeMove(moves[i]);
            }
        }
    }

    public void makeMove(Move move) {
        System.out.println(this);
        System.out.println("Move: " + move);
        int vertical = 0, horizontal = 0;
        switch (move) {
            case Right -> horizontal = 1;
            case Left -> horizontal = -1;
            case Down -> vertical = 1;
            case Up -> vertical = -1;
        }
        int oldIndex = emptyPos.number;
        emptyPos.move(vertical, horizontal);
        int newIndex = emptyPos.number;
        int temp = board[oldIndex];
        board[oldIndex] = board[newIndex];
        board[newIndex] = temp;
    }

    public boolean solvable() {
        int distanceToEmpty = (n - emptyPos.x()) + (m - emptyPos.y());
        return (sign(this.board) ^ (distanceToEmpty % 2)) == 0;
    }

    public int sign(int[] permutation) {
        int s = 0, i = 0;
        while (i < permutation.length) {
            if (permutation[i] != (i+1)%(n*m)) {
                int index = (permutation[i]-1)%(n*m);
                int temp = permutation[i];
                permutation[i] = permutation[index];
                permutation[index] = temp;
                s = 1-s;
            } else {
                i++;
            }
        }
       return s;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(board[i*n+j]);
                sb.append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
