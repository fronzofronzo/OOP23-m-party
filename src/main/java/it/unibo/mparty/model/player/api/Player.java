package it.unibo.mparty.model.player.api;

import it.unibo.mparty.model.player.impl.Character;

/**
 *  This interface models a Player
 */
public interface Player {

    /**
     * Sets the username of the player
     *
     * @param username is the username chosen
     */
    void setUsername(String username);

    /**
     * Get the username of a certain player
     *
     * @return the username of the player
     */
    String getUsername();

    /**
     * Sets the character chosen by the player
     *
     * @param character is the chosen character
     */
    void setCharacter(Character character);

    /**
     * Get the character of a certain player
     *
     * @return the character of the player
     */
    String getCharacter();

    /**
     *
     */
    void setInitialPosition(Position );

}