package it.unibo.mparty.controller.minigames.memorySweep;

import it.unibo.mparty.controller.minigames.MinigameController;
import it.unibo.mparty.utilities.Position;

/**
 * implementation of {@link MemorySweepController}
 */
public interface MemorySweepController extends MinigameController {

    /**
     * manages the setting up of the view and controller.
     */
    void setUp();

    /**
     * manages the click of a button and links it to the model.
     *
     * @param p the position of the button clicked
     */
    void hit(Position p);

    /**
     * sets the random list in the grid.
     */
    void setRandoms();

}