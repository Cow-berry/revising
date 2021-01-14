package model;

public class Cube {
    final int[][][] ROTATION_PREFERENCES = new int[][][]{
            new int[][]{ //x
                    new int[]{0,4,5,2},
                    new int[]{2,2,0,0},
                    new int[]{3,1}
            },
            new int[][]{ //y
                    new int[]{4,3,2,1},
                    new int[]{0,0,0,0},
                    new int[]{0,5}
            },
            new int[][]{ //z
                    new int[]{0,3,5,1},
                    new int[]{3,3,3,3},
                    new int[]{2,4}
            }
    };
    Colors[][][]model; // stored as six 3x3 squares with assigned colors
    public Cube() {
        this.model = new Colors[6][3][3];
        Colors[] colors = Colors.values();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    this.model[i][j][k] = colors[i];
                }
            }
        }
    }

    public Cube(Colors[][][] origin) {
        this.model = new Colors[6][3][3];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                System.arraycopy(origin[i][j], 0, this.model[i][j], 0, 3);
            }
        }
    }

    public Cube apply(Move[] moves) {
        Cube cube = new Cube(model);
        for (Move move: moves) {
            cube = cube.apply(move);
            if (move.twice) {
                cube = cube.apply(move);
            }
        }
        return cube;
    }

    public void rotateFace(int face, boolean clockwise) {
        Colors[][] temp = new Colors[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(this.model[face][i], 0, temp[i], 0, 3);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.model[face][i][j] = clockwise ? temp[2-j][i] : temp[j][2-i];
            }
        }
    }

    public Cube rotateCube(int[][] preference, boolean reverse) {
        int[] cycle = preference[0];
        int[] faceRotations = preference[1];
        int front = preference[2][0];
        int back = preference[2][1];
        Cube result = new Cube(model);
        for (int i = 0; i < 4; i++) {
            int nextI = reverse ? (i+faceRotations.length-1)%4 : (i+1)%4;
            result.model[cycle[nextI]] = model[cycle[i]];
            for (int j = 0; j < (reverse ? faceRotations.length - faceRotations[(i+faceRotations.length-1)%4] : faceRotations[i]); j++) {
                result.rotateFace(cycle[nextI], false);
            }
        }
        result.rotateFace(front, !reverse);
        result.rotateFace(back, reverse);
        return result;
    }

    public Cube x(boolean reverse) {
        return rotateCube(ROTATION_PREFERENCES[0], reverse);
    }

    public Cube y(boolean reverse) {
        return rotateCube(ROTATION_PREFERENCES[1], reverse);
    }

    public Cube z(boolean reverse) {
        return rotateCube(ROTATION_PREFERENCES[2], reverse);
    }

    public Cube rotateUp(boolean clockwise) {
        Cube result = new Cube(model);
        result.rotateFace(0, clockwise);
        for (int i = 0; i < 4; i++) {
            result.model[i+1][0] = model[(clockwise ? (i+1)%4 : (i+3)%4) + 1][0];
        }
        return result;    }

    public Cube apply(Move move) {
        return  switch (move.face) {
            case W -> this.rotateUp(move.clockwise);
            case R -> this.z(false).rotateUp(move.clockwise).z(true);
            case B -> this.x(false).rotateUp(move.clockwise).x(true);
            case O -> this.z(true).rotateUp(move.clockwise).z(false);
            case G -> this.x(true).rotateUp(move.clockwise).x(false);
            case Y -> this.x(false).x(false).rotateUp(move.clockwise).x(false).x(false);
        };
    }

//    public void apply(Move move) {
//        int faceOrdinal = switch (move.face) {
//            case WHITE -> 0;
//            case RED -> 1;
//            case BLUE -> 2;
//            case ORANGE -> 3;
//            case GREEN -> 4;
//            case YELLOW -> 5;
//        };
//        Colors[][] face = this.model[faceOrdinal];
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                this.model[faceOrdinal][i][j] = move.clockwise ? face[2-j][i] : face[j][2-i];
//            }
//        }
//        Colors[][][] oldmodel = new Colors[6][3][3];
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 3; j++) {
//                for (int k = 0; k < 3; k++) {
//                    oldmodel[i][j][k] = this.model[i][j][k];
//                }
//            }
//        }
//
//
//
//        int[] adjacentFaces = switch (faceOrdinal) {
//            case 0 -> new int[]{4, 3, 2, 1};
//            case 1 -> new int[]{0, 2, 5, 4};
//            case 2 -> new int[]{0, 3, 5, 1};
//            case 3 -> new int[]{0, 4, 5, 2};
//            case 4 -> new int[]{0, 1, 5, 3};
//            case 5 -> new int[]{2, 3, 4, 1};
//            default -> throw new IllegalStateException("Unexpected value: " + faceOrdinal);
//        };
//        if (faceOrdinal == 0 || faceOrdinal == 5) {
//            int row = faceOrdinal == 0 ? 0 : 2;
//            for (int i = 0; i < 4; i++) {
//                int newIndex = (move.clockwise ? i+1 : 4-(i+1))%4;
//                this.model[adjacentFaces[newIndex]][row] = oldmodel[adjacentFaces[i]][row];
//            }
//        } else if (faceOrdinal == 2 || faceOrdinal == 4) {
//            int row = faceOrdinal == 2 ? 2 : 0;
//            for (int i = 0; i < 3; i++) {
//                int newIndex = faceOrdinal == 2 ? i : 2-i;
//                this.model[0][row][newIndex] = move.clockwise ? oldmodel[adjacentFaces[3]][2-i][2] : oldmodel[adjacentFaces[1]][i][0];
//                this.model[adjacentFaces[1]][i][0] = move.clockwise ? oldmodel[0][row][newIndex] : oldmodel[5][2-row][2-newIndex];
//                this.model[5][2-row][newIndex] = move.clockwise ? oldmodel[adjacentFaces[1]][2-i][2] : oldmodel[adjacentFaces[3]][i][0];
//                this.model[adjacentFaces[3]][i][2] = move.clockwise ? oldmodel[5][2-row][newIndex] : oldmodel[0][row][2-newIndex];
//            }
//        } else {
//            int column = faceOrdinal == 3 ? 2 : 0;
//            for (int i = 0; i < 3; i++) {
//                int newIndex = faceOrdinal == 3 ? -1 : -1 // Very bad code overall. There must be a better way!
//                this.model[0][i][column] = move.clockwise ? oldmodel[adjacentFaces[3]][i][2] : oldmodel[adjacentFaces[1]][2-i][0];
//                this.model[adjacentFaces[1]][i][0] = move.clockwise ? oldmodel[0][i][column] : oldmodel[5][i][column];
//                this.model[5][i][column] = !move.clockwise ? oldmodel[adjacentFaces[3]][i][2] : oldmodel[adjacentFaces[1]][2-i][0];
//                this.model[adjacentFaces[3]][i][2] = move.clockwise ? oldmodel[adjacentFaces[3]][i][2] : oldmodel[adjacentFaces[1]][i][0];
//            }
//
//        }
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        showOne(0, sb);
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < 5; j++) {
                for (int k = 0; k < 3; k++) {
                    sb.append(this.model[j][i][k]);
                }
            }
            sb.append('\n');
        }
        showOne(5, sb);
        return sb.toString();
    }

    private void showOne(int face, StringBuilder sb) {
        for (int i = 0; i < 3; i++) {
            sb.append("   ".repeat(3));
            for (int j = 0; j < 3; j++) {
                sb.append(this.model[face][i][j]);
            }
            sb.append('\n');
        }
    }
}
