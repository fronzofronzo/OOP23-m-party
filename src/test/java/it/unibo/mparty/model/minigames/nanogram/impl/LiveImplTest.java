package it.unibo.mparty.model.minigames.nanogram.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.mparty.model.minigames.nanogram.live.impl.LiveImpl;
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
    private LiveImpl live;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        this.live = new LiveImpl();
    }

    /**
     * Tests the {@code reset} method.
     * Ensures that the lives are reset to the initial value.
     */
    @Test
    @SuppressFBWarnings(value = "UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR",
            justification = "The field 'live' is initialized in the setUp method, which is run before each test.")
    void reset() {
        this.live.reset();
        assertEquals(INITIAL_LIVES, this.live.getLive());
    }

    /**
     * Tests the {@code isDeath} method.
     * Ensures that the method correctly identifies when lives reach zero.
     */
    @Test
    @SuppressFBWarnings(value = "UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR",
            justification = "The field 'live' is initialized in the setUp method, which is run before each test.")
    void isDeath() {
        this.live.decrease();
        assertFalse(this.live.isDeath());

        this.live.decrease();
        this.live.decrease();
        assertTrue(this.live.isDeath());
    }
}
