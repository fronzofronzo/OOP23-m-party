package it.unibo.mparty.view.minigames.secretcode;

import java.util.List;

import it.unibo.mparty.model.minigames.ciao.util.SecretCodeColors;
import it.unibo.mparty.model.minigames.ciao.util.SecretCodeResults;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.minigames.MinigameView;

/**
 * This class model the view of the game Secret Code based on the pattern MVC
 * and extends {@link MinigameView}.
 */
public interface SecretCodeView extends MinigameView {

    /**
     * Update the guess of the input player.
     * 
     * @param player the player to be updated.
     * @param pos    a {@link Pair} that indicates which guess has to be updated.
     * @param guess  the {@link SecretCodeColors} of the guess.
     */
    void updateGuess(String player, Pair<Integer, Integer> pos, SecretCodeColors guess);

    /**
     * Update the result of a guess.
     * 
     * @param player  the player to be updated.
     * @param turn    the current turn.
     * @param results the {@link SecretCodeResults}.
     */
    void updateResults(String player, int turn, List<SecretCodeResults> results);

    /**
     * Show the soluction of the game.
     * 
     * @param solution the soluction.
     */
    void showSolution(List<SecretCodeColors> solution);
}
