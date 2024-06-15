package it.unibo.mparty.model.gameBoard.util;

public record Coordinate(int x, int y) {

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}