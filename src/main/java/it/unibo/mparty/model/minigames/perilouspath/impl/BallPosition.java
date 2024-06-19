package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.List;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;

public class BallPosition extends AbstractPosition{

    private static final int MINUS = 1;


    public BallPosition(int x, int y, int size) {
        super(x, y, size);
        
    }

    @Override
    public boolean isSafe(List<AbstractPosition> list) {
        return this.getY() == 0 || this.getY() == this.getSize() - MINUS;
    }

    
}