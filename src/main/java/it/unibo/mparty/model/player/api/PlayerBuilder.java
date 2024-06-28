package it.unibo.mparty.model.player.api;

import it.unibo.mparty.model.player.impl.Character;
import it.unibo.mparty.utilities.Position;

/**
 * The {@code PlayerBuilder} interface defines the methods to create a new
 * player. It applies the creation pattern Builder
 * Example usage:
 *<pre>{@code
 *     PlayerBuilder builder = new PlayerBuilder();
 *     Player player = PlayerBuilder.username("player")
 *                                  .character("Mario")
 *                                  .build()
 }</pre>
 */
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
     * Create the player instance with the specified parameters
     * @return player instance
     */
    Player buildPlayer();
}
