package it.unibo.mparty.model.minigames.perilouspath.api;

import it.unibo.mparty.model.minigames.perilouspath.impl.Pair;

public abstract class AbstractPosition {

    private Pair<Integer,Integer> position;
    private int size;
    private static int ADJ = 1;

    public AbstractPosition(int x,int y,int size){
        this.position = new Pair<>(x,y);
        this.size = size;
    }

    /*
     * getter for the x of a position
     */
    public int getX(){
        return position.getX();
    }

    /*
     * getter for the y of a position
     */
    public int getY(){
        return position.getY();
    }

    /**
     * 
     * @param p the position we want to check wheter is adjacent to this
     * @return true if they are adjacent, false otherwise
     */
    public boolean adjacent(AbstractPosition p){
        return Math.abs(this.getX() - p.getX()) <= ADJ || Math.abs(this.getY() - p.getY()) <= ADJ;
    }
}