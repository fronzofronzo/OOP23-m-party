package it.unibo.mparty.model.minigames.perilouspath.api;

import it.unibo.mparty.model.minigames.MinigameModel;

import java.util.List;

/**
 * interface that model the model for the Perilous Path mini game
 */
public interface PerilousPath extends MinigameModel {

    /**
     * the types of buttons present a grid
     * a button can either be a ball,bomb or a path.
     * the type wrong signifies that that button is a path-type button, but
     * it does not respect the politics of the path-type buttons
     */
    enum Type{
        BOMB,BALL,PATH,WRONG
    }

    /**
     * sets the positions of bombs in the grid in a pseudo-random manner
     */
    void setBombs();

    /**
     * sets the positions of balls in the grid in a pseudo-random manner
     */
    void setBalls();

    /**
     * @return the list of positions of bombs that are in the grid
     */
    List<AbstractPosition> getBombs();

    /**
     * @return the list of positions of balls that are in the grid
     */
    List<AbstractPosition> getBalls();

    /**
     * 
     * @param p the position of the button that was hit by the player in the grid
     * @return the type of that button
     */
    Type hit(AbstractPosition p);

    /**
     * @return true whether the game is over or not
     */
    boolean isOver();

    /**
     * @return the side of the grid which is a square
     */
    int getSize();
    
}