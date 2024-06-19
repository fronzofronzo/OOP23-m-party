package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.List;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;

public class PathPosition extends AbstractPosition{

    /*
     * constructor which calls the constructor of AbstractPosition
     */
    public PathPosition(int x, int y, int size) {
        super(x, y, size);
    }

    /**
     * implementation of the abstract method isSafe(),
     * in this case a position can be safe only if it is near at least to another position
     */
    @Override
    public boolean isSafe(List<AbstractPosition> list) {
        return list.size() == 0 ? true : list.stream().anyMatch(p -> this.inOrizzontal(p) || this.inOrizzontal(p));
    }

    
}