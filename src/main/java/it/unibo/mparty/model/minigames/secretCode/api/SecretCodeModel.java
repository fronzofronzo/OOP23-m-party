
package it.unibo.mparty.model.minigames.secretcode.api;

import java.util.List;

import it.unibo.mparty.model.minigames.MinigameModel;
import it.unibo.mparty.model.minigames.secretcode.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.secretcode.util.SecretCodeResults;

/**
 * This interface models the Model of the pattern "MVC" of the mini-game Secrete
 * Code.
 */
public interface SecretCodeModel extends MinigameModel {

    /**
     * If the current guess is completed, return the result, otherwise return an
     * empty list.
     * 
     * @return return the result, otherwise return an empty list.
     */
    List<SecretCodeResults> getGuessResult();

    /**
     * Get the current guess.
     * 
     * @return the list of the color that was added at the current guess.
     */
    List<SecretCodeColors> getCurrentGuess();

    /**
     * Return the soluction of this game.
     * 
     * @return the soluction of this game.
     */
    List<SecretCodeColors> getSoluction();

    /**
     * Add the input color at the current guess if it is possible.
     * 
     * @param color the color to be added.
     * @return true if it was added correctly, false otherwise.
     */
    boolean addColor(SecretCodeColors color);

    /**
     * Return the current player's name.
     * 
     * @return the name of the current player.
     */
    String getCurrentPlayer();

    /**
     * Get the name of the winner player if the game is over.
     * 
     * @return the name of the winner player if the game is over, null otherwise.
     */
    String getWinner();

    /**
     * Get the actual turn of the game.
     * 
     * @return the turn.
     */
    int getTurn();
}
