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
    public boolean isSafe(List<AbstractPosition> list1,List<AbstractPosition> list2) {
        if(list1.size() == 0){
            if(!this.equals(list2.get(0)) && isClose(list2.get(0))){
                return true;
            }
            return false;
        }
        return list1.stream().anyMatch(p -> this.inOrizzontal(p) || this.inVertical(p));
    }

    /**
     * @param p the AbstractPosition to check wheter is inVertical or inOrizzontal  to this
     * @return true if th eAbstractPosition p is inOrizzontal or inVertical to this
     */
    private boolean isClose(AbstractPosition p){
        return this.inOrizzontal(p) || this.inVertical(p);
    }

    
}