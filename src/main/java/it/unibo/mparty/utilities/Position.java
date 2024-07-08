package it.unibo.mparty.utilities;

public record Position(int x, int y) {

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public static Position getStandardPosition(){
        return new Position(0,0);
    }
}