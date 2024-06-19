package it.unibo.mparty.model.minigames.perilouspath.api;

import java.util.List;

public interface PerilousPath {

    /**
     * the types of buttons present a grid
     * a button can either be a ball,bomb or a path.
     * the type wrong signifies that that button is a path-type button but 
     * it does not respects the politics of the path-type buttons
     */
    public enum Type{
        BOMB,BALL,PATH,WRONG
    }

    /**
     * sets the positions of bombs in the grid in a pseudo-random manner
     */
    public void setBombs();

    /**
     * sets the positions of balls in the grid in a pseudo-random manner
     */
    public void setBalls();

    /**
     * @return the list of positions of bombs that are in the grid
     */
    public List<AbstractPosition> getBombs();

    /**
     * @return the list of positions of balls that are in the grid
     */
    public List<AbstractPosition> getBalls();

    /**
     * 
     * @param p the position of the button that was hit by the player in the grid
     * @return the type of that button
     */
    public Type hit(AbstractPosition p);

    /**
     * @return true wheter the game is over or not
     */
    public boolean isOver();
    
}