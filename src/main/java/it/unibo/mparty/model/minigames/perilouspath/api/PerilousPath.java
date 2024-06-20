package it.unibo.mparty.model.minigames.perilouspath.api;

import java.util.List;

public interface PerilousPath {
    public enum Type{
        BOMB,BALL,PATH,WRONG
    }

    
    public void setBombs();

    public void setBalls();

    public List<AbstractPosition> getBombs();

    public List<AbstractPosition> getBalls();

    public Type hit(AbstractPosition p);

    public boolean isOver();
    
}