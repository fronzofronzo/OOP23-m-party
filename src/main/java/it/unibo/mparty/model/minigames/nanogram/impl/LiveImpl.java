package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.Live;
import it.unibo.mparty.model.minigames.nanogram.api.NanogramModel;

public class LiveImpl implements Live {
    private static final int INITIAL_LIVES = 3;
    private int lives;
    private final NanogramModelImpl model = new NanogramModelImpl();

    @Override
    public void reset() {
        lives = INITIAL_LIVES;
    }

    @Override
    public void decrease() {
        if (!model.isMoveValid()) {
            lives--;
        }
    }

    @Override
    public boolean isDeath() {
        return lives <= 0;
    }
}
