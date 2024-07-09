package it.unibo.mparty.view.endGame.api;

import it.unibo.mparty.utilities.Pair;

import java.util.Map;

/**
 * The EndGameView interface provides a method for displaying the results at the end of a game.
 */
public interface EndGameView {

    /**
     * Displays the results of the game.
     *
     * @param result a map where the key is the player's username and the value is a pair containing
     *               the number of stars and coins the player has respectively.
     */
    void showResults(Map<String, Pair<Integer, Integer>> result);
}
