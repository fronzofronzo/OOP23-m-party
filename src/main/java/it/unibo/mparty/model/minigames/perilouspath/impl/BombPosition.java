package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;

public class BombPosition extends AbstractPosition{

    final private static int MAX_NEAR_BOMBS = 3;
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
    public boolean isSafe(List<AbstractPosition> list1,List<AbstractPosition> list2) {
        if(list1.isEmpty() && !this.SamePosition(list2)){
            return true;
        }
        return list1.stream().filter(this::adjacent).count() <= MAX_NEAR_BOMBS && !this.SamePosition(list2);
    }

    private boolean SamePosition(List<AbstractPosition> list){
        return list.stream().anyMatch(b -> (this.getX() == b.getX() && this.getY() == b.getY()));
    }

}