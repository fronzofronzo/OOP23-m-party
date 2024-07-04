package it.unibo.mparty.model;

import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Interface that models the model of the main game
 */
public interface GameModel {

    /**
     * Moves the current player to a new position
     *
     * @return
     */
    boolean movePlayer(Optional<Direction> dir);

    Set<Direction> getDirections();

    /**
     * Roll dices of the actual player
     * @return the result of the roll
     */
    int rollDice();

    /**
     * If is active, get the name of the minigame.
     * @return {@link Optional}: it's empty if there is no minigame running
     * at that moment. If there's a minigame playing, the Optional contains
     * its name
     */
    Optional<String> getActiveMinigame();

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

    /**
     * Method to end the current minigame and update the model
     * with the results
     * @param result of the minigame
     */
    void endMinigame(Pair<String,Integer> result);

    List<String> getPlayersNicknames();

    /**
     * Get the information of the player that's playing the actual turn
     * @return {@link Pair} containing nickname of player and it's
     * {@link Position}
     */
    Pair<String, Position> getActualPlayerInfo();

}
