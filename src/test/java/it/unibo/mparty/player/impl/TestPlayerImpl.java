package it.unibo.mparty.player.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImpl;
import it.unibo.mparty.utilities.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;

/**
 * Test class for a {@link PlayerImpl} class.
 */
class TestPlayerImpl {

    private static final int RANDOM_BOUND = 100;
    private Player testPlayer;

    /**
     * Configuration step: this is performed before each test.
     */
    @BeforeEach
    void init() {
        final String username = "username";
        final String character = "Luigi";
        testPlayer = new PlayerImpl(username, character);
    }

    /**
     * Check that the Player implementation is created with the correct values.
     */
    @Test
    void testPlayerInitialization() {
        assertEquals(0, testPlayer.getNumStars());
        assertEquals(0, testPlayer.getNumCoins());
    }

    /**
     * Check that the operation of star adding and removal work properly.
     */
    @Test
    void testStarAmount() {
        assertEquals(0, testPlayer.getNumStars());
        testPlayer.addStar();
        assertEquals(1, testPlayer.getNumStars());
        testPlayer.removeStar();
        assertEquals(0, testPlayer.getNumStars());
    }

    /**
     * Check that the operation of coins adding and removal work properly.
     */
    @Test
    void testCoinsAmount() {
        final int numCoins = 5;
        assertEquals(0, testPlayer.getNumCoins());
        testPlayer.addCoins(numCoins);
        assertEquals(numCoins, testPlayer.getNumCoins());
        testPlayer.removeCoins(numCoins);
        assertEquals(0, testPlayer.getNumCoins());
    }

    /**
     * Check that position is correctly modified.
     */
    @Test
    void testPosition() {
        final Random random = new Random();
        final Position position = new Position(random.nextInt(RANDOM_BOUND), random.nextInt(RANDOM_BOUND));
        this.testPlayer.setPosition(position);
        assertEquals(position, this.testPlayer.getPosition());
    }

}
