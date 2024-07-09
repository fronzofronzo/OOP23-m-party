package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.List;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;

/**
 * implementation of {@link AbstractPosition} that models a path in a grid of button
 */
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
    public boolean isSafe(List<AbstractPosition> list1,List<AbstractPosition> list2) {
        if(list1.isEmpty()){
            var firstBall = list2.get(0);
            return !(this.getX() == firstBall.getX() && this.getY() == firstBall.getY()) && this.isClose(list2.get(0));
        }
        return list1.stream().anyMatch(p -> this.inHorizontal(p) || this.inVertical(p));
    }

    /**
     * @param p the AbstractPosition to check whether is inVertical or inHorizontal  to this
     * @return true if th eAbstractPosition p is inHorizontal or inVertical to this
     */
    private boolean isClose(AbstractPosition p){
        return this.inHorizontal(p) || this.inVertical(p);
    }

}
