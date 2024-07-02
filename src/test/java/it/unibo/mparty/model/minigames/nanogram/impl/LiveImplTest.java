package it.unibo.mparty.model.minigames.nanogram.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiveImplTest {

    private static final int INITIAL_lIVES = 3;
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
        assertEquals(INITIAL_lIVES, this.live.getLive());
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

        this.live.update(INITIAL_lIVES);
        assertFalse(this.live.isDeath());
    }
}