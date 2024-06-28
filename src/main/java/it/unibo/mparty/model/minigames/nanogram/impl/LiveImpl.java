package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Live;

/**
 * Implementation of the Live interface for managing lives in a game.
 * This class tracks the number of lives a player has and provides methods to
 * retrieve, reset, update, and check the status of lives.
 */
public class LiveImpl implements Live {
    private static final int INITIAL_LIVES = 3;
    private int lives;

    /**
     * Constructs a LiveImpl instance with the initial number of lives set to {@value #INITIAL_LIVES}.
     */
    public LiveImpl() {
        this.lives = INITIAL_LIVES;
    }

    @Override
    public int getLive() {
        return this.lives;
    }

    @Override
    public void reset() {
        this.lives = INITIAL_LIVES;
    }

    @Override
    public void update(final int live) {
        this.lives = live;
    }

    @Override
    public boolean isDeath() {
        return this.lives <= 0;
    }
}
