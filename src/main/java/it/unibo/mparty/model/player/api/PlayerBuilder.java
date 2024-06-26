package it.unibo.mparty.model.player.api;

import it.unibo.mparty.model.player.impl.Character;
import it.unibo.mparty.utilities.Position;

public interface PlayerBuilder {

    /**
     * Sets the username of player
     * @param username of player
     * @return current builder
     */
    PlayerBuilder username(String username);

    /**
     * Sets the character chosen by the player
     * @param character chosen for the player
     * @return current builder
     */
    PlayerBuilder character(String character);

    /**
     * Sets the initial position of player
     * @param position of player at the start of the game
     * @return current builder
     */
    PlayerBuilder position(Position position);

    /**
     * Create the player instance with the specified parameters
     * @return player instance
     */
    Player buildPlayer();
}
