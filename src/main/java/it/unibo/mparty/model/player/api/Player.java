package it.unibo.mparty.model.player.api;

import it.unibo.mparty.model.player.Position;
import it.unibo.mparty.model.player.impl.Character;

/**
 *  This interface models a Player
 */
public interface Player {

    /**
     * Get the username of a certain player
     *
     * @return the username of the player
     */
    String getUsername();

    /**
     * Get the character of a certain player
     *
     * @return the character of the player
     */
    Character getCharacter();

    /**
     * Get the current position of the player
     *
     * @return the position
     */
    Position getPosition();

}