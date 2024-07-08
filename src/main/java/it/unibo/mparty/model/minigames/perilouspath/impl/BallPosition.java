package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.List;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
/**
 * Implementation of AbstractPosition for balls, which has a special isSafe() politics
 */
public class BallPosition extends AbstractPosition{

    private static final int MINUS = 1;

    /*
     * constructor which calls the constructor of AbstractPosition
     */
    public BallPosition(int x, int y, int size) {
        super(x, y, size);   
    }

    /**
     * implementation of the abstract method isSafe(), 
     * in this case the position is safe only if it is on the first or the last column
     */
    @Override
    public boolean isSafe(List<AbstractPosition> list1, List<AbstractPosition> list2) {
        return (this.getY() == 0 || this.getY() == this.getSize() - MINUS) && (this.getX() != 0 && this.getX() != this.getSize() - MINUS);
    }

    
}