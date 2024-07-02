package it.unibo.mparty.model.minigames;

import it.unibo.mparty.model.gameBoard.util.Pair;

import java.util.List;

/**
 * This interface models a generic Model for a minigame. Each minigame has to be set
 * with the players that play the minigame and each minigame should return the results
 * of the minigame.
 */
public interface MinigameModel {

    /**
     * Method to get the result of the minigame
     * @return {@link Pair} of {@link String} that's the nickname of the player
     * who won ( if it's single-player, return the nickname of the only participant)
     * and {@link Integer} num of coins earned by the player.
     */
    Pair<String, Integer> getResult();

    void setUpPlayers(List<String> players);
}
