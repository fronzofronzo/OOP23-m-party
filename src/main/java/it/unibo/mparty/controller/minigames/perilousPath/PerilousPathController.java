package it.unibo.mparty.controller.minigames.perilousPath;

import it.unibo.mparty.controller.minigames.MinigameController;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;

/**
 * interface that models the controller of the mini-game Perilous Path
 */
public interface PerilousPathController extends MinigameController {
    /**
     * methods that manages the setting up of the view and model of Perilous Path mini-game
     */
    void setUp() throws InterruptedException;

    /**
     * method that manages the click of a button in the view and links it with the model
     * @param p the position of the button clicked in the grid
     */
    void hit(AbstractPosition p);

}
