package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;

public class BombPosition extends AbstractPosition{

    final private static int MAX_NEAR_BOMBS = 2;
    final private static int EMPTY = 0;

    /*
     * constructor which calls the constructor of AbstractPosition
     */
    public BombPosition(int x, int y, int size) {
        super(x, y, size);
    }
    
    /**
     * implementation of the method isSafe(),
     * in this case a position is safe only if it can be at most close to another position
     */
    @Override
    public boolean isSafe(List<AbstractPosition> list) {
        if(list.size() == EMPTY){
            return true;
        }
        return list.stream().filter(b -> this.adjacent(this)).collect(Collectors.toList()).size() <= MAX_NEAR_BOMBS;
    }

    
}