package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.List;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;

/**
 * implementation of {@link AbstractPosition} that models a bomb in a grid of button
 */
public class BombPosition extends AbstractPosition{

    final private static int MAX_NEAR_BOMBS = 3;

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

    /**
     * method for knowing whether this is in the same position of a button in a list
     * @param list the list of positions to be checked
     * @return true whether the condition is true, false otherwise
     */
    private boolean SamePosition(List<AbstractPosition> list){
        return list.stream().anyMatch(b -> (this.getX() == b.getX() && this.getY() == b.getY()));
    }

}
