package it.unibo.mparty.utilities;

public enum Connect4Directions {
    HORIZONTAL (0,1),
    VERTICAL (1,0),
    DIAGONAL_LEFT (1,-1),
    DIAGONAL_RIGHT (1,1);

    private Position pos;

    private Connect4Directions (int row, int col) {
        pos = new Position(row, col);
    }

    public Position getPosition () {
        return this.pos;
    }
}
