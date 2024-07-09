package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBuilder;

/**
 * This class implements {@link PlayerBuilder} interface. It provides an implementation
 * for a builder of player. It stores the username and the character chosen  and
 * return a new {@link Player} once the method build is called.
 */
public class PlayerBuilderImplementation implements PlayerBuilder {

    private String username;
    private String character;

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerBuilder username(final String username) {
        this.username = username;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerBuilder character(final String character) {
        this.character = character;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player buildPlayer() throws IllegalStateException {
        if (this.username == null  || this.character == null) {
            throw new IllegalStateException("Missing fields: cannot create a new Player");
        }
        return new PlayerImplementation(this.username, this.character);
    }
}
