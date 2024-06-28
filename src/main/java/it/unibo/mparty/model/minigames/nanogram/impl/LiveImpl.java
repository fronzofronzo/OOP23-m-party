package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Live;

public class LiveImpl implements Live {
    private static final int INITIAL_LIVES = 3;
    private int lives;

    @Override
    public int getLive() {
        return this.lives;
    }

    @Override
    public void reset() {
        this.lives = INITIAL_LIVES;
    }

    @Override
    public void update(int live) {
        this.lives = live;
    }

    @Override
    public boolean isDeath() {
        return this.lives <= 0;
    }
}
