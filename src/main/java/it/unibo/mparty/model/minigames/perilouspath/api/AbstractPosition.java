package it.unibo.mparty.model.minigames.perilouspath.api;

import java.util.List;

import it.unibo.mparty.model.minigames.perilouspath.impl.Pair;
/*
 * An abstract class for implementing the position  of a button inside a grid.
 * A generic button has a generic position (x,y) inside a cartesian system.
 */
public abstract class AbstractPosition {

    final private Pair<Integer,Integer> position;
    final private int size;
    final private static int ADJ = 1;

    /**
     * 
     * @param x 
     * @param y
     * @param size the size of the gri
     */
    public AbstractPosition(int x,int y,int size){
        this.position = new Pair<>(x,y);
        this.size = size;
    }

    /**
     * 
     * @return the x position of a button
     */
    public int getX(){
        return position.getX();
    }

    /**
     * 
     * @return the y position of a button
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

    /**
     * @return the size of the grid
     */
    public int getSize(){
        return this.size;
    }

    /**
     * the politics regarding wheter the position of a button inside a grid is safe or not, it dipends on the implementation 
     * @param list list of positions of buttons to compare to this to know if it is safe
     * @return true if the position is safe, false otherwise
     */
    public abstract boolean isSafe(List<AbstractPosition> list);
}