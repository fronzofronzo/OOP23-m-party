package it.unibo.mparty.player.impl;

import it.unibo.mparty.model.player.api.Dice;
import it.unibo.mparty.model.player.impl.DiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for a {@link DiceImpl} class.
 */
class DiceImplTest {

    private Dice testDice;

    /**
     * Configuration step: this is performed before each step.
     */
    @BeforeEach
    void init() {
        testDice = new DiceImpl();
    }

    /**
     * Check if the bounds modify is correct.
     */
    @Test
    void testBoundsModify() {
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
    void testAttemptsModify() {
        final int numAttempts = 3;
        this.testDice.setNumberOfAttempts(numAttempts);
        assertEquals(numAttempts, this.testDice.getNumOfAttempts());
    }

    /**
     * Check that the result of the dice is a number between bounds.
     */
    @Test
    void rollingDice() {
        final int min = this.testDice.getBounds().getFirst() * this.testDice.getNumOfAttempts();
        final int max = this.testDice.getBounds().getSecond() * this.testDice.getNumOfAttempts();
        this.testDice.rollDice();
        final int diceResult = this.testDice.getResult();
        assertTrue(diceResult >= min && diceResult <= max);
    }

}
