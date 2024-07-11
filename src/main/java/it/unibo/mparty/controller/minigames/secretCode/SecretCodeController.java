package it.unibo.mparty.controller.minigames.secretCode;

import it.unibo.mparty.controller.minigames.MinigameController;
import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;

/**
 * Interface that models the controller of the minigame Secrete Code. It
 * represents the Controller of the patter MVC.
 */
public interface SecretCodeController extends MinigameController {

    /**
     * Add the {@link SecretCodeColors} to the current guess of the current player
     * if the game is not over.
     * If is possible to add the new {@link SecretCodeColors} then update the view.
     * 
     * @param color the color to be added to the current guess.
     */
    void addColor(SecretCodeColors color);

    /**
     * If the game is not over and is possible to compute a result, then update the
     * view.
     */
    void guess();

}
