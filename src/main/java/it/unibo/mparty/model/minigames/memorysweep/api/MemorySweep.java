package it.unibo.mparty.model.minigames.memorysweep.api;

import java.util.Set;

import it.unibo.mparty.model.minigames.MinigameModel;
import it.unibo.mparty.utilities.Position;

/**
 * interface that models the view of the mini-game Memory Sweep
 */
public interface MemorySweep extends MinigameModel {

    /**
     * enum describing the types of event that can happen when a button is clicked.
     */
    enum HitType {
        /**
         * the player guessed the right button of the sequence that needs to be recreated but the guess isn't over.
         */
        RIGHT_CHOICE,
        /**
         * TURN_END: the player guessed right all the buttons of the sequence which needed to be recreated.
         * now it's the other player turn
         */
        TURN_END,
        /**
         * LOSS: the player guessed the wrong button, so he has lost.
         */
        LOSS
    }

    /**
     * method for setting the random list that the players should try to recreate.
     *
     * @return the list of random buttons to be recreated by players
     */
    Set<Position> getRandomList();

    /**
     * method for getting the random list that the players should try to recreate.
     */
    void setRandomList();

    /**
     * method for knowing whether a player is done with his try.
     *
     * @param p the position clicked by a player
     * @return whether the list of buttons clicked by the players is finished or not
     */
    HitType hit(Position p);

    /**
     * method for getting which turn is it.
     *
     * @return true if it is the turn of one player, false if it is the turn of the other player
     */
    boolean getTurn();

    /**
     * method for getting the winner of the game.
     *
     * @return the winner of the game
     */
    String getWinner();

    /**
     * method for getting the size of the list that needs to be recreated.
     *
     * @return the size of the list
     */
    int getCounter();

}
