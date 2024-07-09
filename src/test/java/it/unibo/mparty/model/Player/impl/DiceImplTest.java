package it.unibo.mparty.model.Player.impl;

import static org.junit.jupiter.api.Assertions.*;

import it.unibo.mparty.model.player.api.Dice;
import it.unibo.mparty.model.player.impl.DiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for a {@link DiceImpl} class
 */
class DiceImplTest {

    private Dice testDice;

    /**
     * Configuration step: this is performed before each step.
     */
    @BeforeEach
    public void init() {
        testDice = new DiceImpl();
    }

    /**
     * Check if the bounds modify is correct.
     */
    @Test
    public void testBoundsModify() {
        final int minBound = 3;
        final int maxBound = 5;
        this.testDice.setMinNumber(minBound);
        this.testDice.setMaxNumber(maxBound);
        assertEquals(minBound, this.testDice.getBounds().getFirst());
        assertEquals(maxBound, this.testDice.getBounds().getSecond());
    }

    /**
     * Check if the number of attempts modify is correct.
     */
    @Test
    public void testAttemptsModify() {
        final int numAttempts = 3;
        this.testDice.setNumberOfAttempts(numAttempts);
        assertEquals(numAttempts, this.testDice.getNumOfAttempts());
    }

}
