package it.unibo.mparty.model.player;

public record Position(int x, int y) {

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}
