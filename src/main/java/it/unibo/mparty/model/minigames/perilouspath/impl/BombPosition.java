package it.unibo.mparty.model.minigames.perilouspath.impl;

import java.util.List;

import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;

public class BombPosition extends AbstractPosition{

    public BombPosition(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    public boolean isSafe(List<AbstractPosition> list) {
        if(list.size() == 0){
            return true;
        }
        return list.stream().allMatch(b -> !this.adjacent(b));
    }

    
}