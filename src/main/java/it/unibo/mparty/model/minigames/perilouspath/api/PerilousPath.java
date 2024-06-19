package it.unibo.mparty.model.minigames.perilouspath.api;

import java.util.List;

public interface PerilousPath {
    public enum type{
        BOMB,BALL,PATH
    }

    
    public void setBombs();

    public void setBalls();

    public List<AbstractPosition> getBombs();

    public List<AbstractPosition> getBalls();
    
}