package it.unibo.mparty.model.minigames.nanogram.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class LiveImplTest {

    private static final int INITIAL_LIVES = 3;
    private static final int UPDATE_LIVE = 1;
    private static final int DEATH_LIVE = 0;
    private LiveImpl live;

    @BeforeEach
    void setUp() {
        this.live = new LiveImpl();
    }

    @Test
    void reset() {
        this.live.reset();
        assertEquals(INITIAL_LIVES, this.live.getLive());
    }

    @Test
    void update() {
        this.live.update(UPDATE_LIVE);
        assertEquals(UPDATE_LIVE, this.live.getLive());
    }

    @Test
    void isDeath() {
        this.live.update(DEATH_LIVE);
        assertTrue(this.live.isDeath());

        this.live.update(INITIAL_LIVES);
        assertFalse(this.live.isDeath());
    }
}
