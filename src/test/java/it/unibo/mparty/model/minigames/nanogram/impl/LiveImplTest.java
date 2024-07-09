package it.unibo.mparty.model.minigames.nanogram.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for {@link LiveImpl}.
 */
class LiveImplTest {

    private static final int INITIAL_LIVES = 3;
    private static final int UPDATE_LIVE = 1;
    private static final int DEATH_LIVE = 0;
    private LiveImpl live;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        this.live = new LiveImpl();
    }

    /**
     * Tests the {@code reset} method.
     * Ensures that the lives are reset to the initial value.
     */
    @Test
    void reset() {
        this.live.reset();
        assertEquals(INITIAL_LIVES, this.live.getLive());
    }

    /**
     * Tests the {@code update} method.
     * Ensures that the lives are updated correctly.
     */
    @Test
    void update() {
        this.live.update(UPDATE_LIVE);
        assertEquals(UPDATE_LIVE, this.live.getLive());
    }

    /**
     * Tests the {@code isDeath} method.
     * Ensures that the method correctly identifies when lives reach zero.
     */
    @Test
    void isDeath() {
        this.live.update(DEATH_LIVE);
        assertTrue(this.live.isDeath());

        this.live.update(INITIAL_LIVES);
        assertFalse(this.live.isDeath());
    }
}
