package it.unibo.mparty.model;

import it.unibo.mparty.utilities.Position;

import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

import java.util.Map;

/**
 * Interface that models the model of the main game
 */
public interface GameModel {

    /**
     * Moves the current player to a new position
     *
     * @return
     */
    Position movePlayer();

    /**
     * Roll dices of the actual player
     * @return the result of the roll
     */
    int rollDice();

    /**
     * Get the name of the minigame that is active
     * @return the name of the minigame
     */
    String getActiveMinigame();

    /**
     * Check if during the actual player turn, there is a minigame active
     * @return true if there is a minigame active, false otherwise
     */
    boolean isActiveMinigame();

    /**
     * End the turn of the actual player and starts the turn of the
     * next player
     */
    void nextPlayer();

    /**
     * Get the username of the player that has won
     * @return the username of winner
     */
    String getWinner();

    /**
     * Checks if the game's over
     * @return true if the game is over, false otherwise
     */
    boolean isOver();

    /**
     * Activate the effect of slot where is the player that's playing its turn
     */
    void activateSlot();

    /**
     * Get the board configuration: for each slot, returns the relative slot type
     * @return {@link Map} of {@link Position} and {@link SlotType}
     */
    Map<Position, SlotType> getBoardConfiguration();

    /**
     * Get the board width and height
     * @return {@link Pair} containing dimensions
     */
    Pair<Integer, Integer> getBoardDimensions();

}
