package it.unibo.mparty.model.player.api;

import it.unibo.mparty.model.player.impl.Character;
import it.unibo.mparty.utilities.Position;

/**
 *  This interface models a Player. Each player has a username, character,
 *  position and an amount of coins and stars. The interface provides methods
 *  to get information about the player and to modify the parameters.
 */
public interface Player {

    /**
     * Get the username of a certain player.
     * @return the username of the player.
     */
    String getUsername();

    /**
     * Get the character of a certain player.
     *
     * @return the character of the player.
     */
    Character getCharacter();

    /**
     * Get the current position of the player.
     *
     * @return the position.
     */
    Position getPosition();

    /**
     * Move the player to a certain position.
     *
     * @param position where move the player.
     */
    void setPosition(Position position);

    /**
     * Add a certain amount of coins to the player.
     * @param num of coins to be added.
     */
    void addCoins(int num);

    /**
     * Remove a certain amount of coins to the player.
     * @param num of coins to be removed.
     */
    void removeCoins(int num);

    /**
     * Get the amount of money of the player.
     * @return the number of money.
     */
    int getNumCoins();

    /**
     * Add one star to the player.
     */
    void addStar();

    /**
     * Get the number of stars of the player.
     * @return the number of stars.
     */
    int getNumStars();

    /**
     * Remove one star from the player.
     */
    void removeStar();

    /**
     * Access to player bag.
     * @return the player bag.
     */
    PlayerBag getPlayerBag();

    /**
     * Access to the player's dice.
     * @return the player's dice.
     */
    Dice getDice();
}
