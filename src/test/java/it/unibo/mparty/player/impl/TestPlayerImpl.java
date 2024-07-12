package it.unibo.mparty.player.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    private static final Random RANDOM = new Random();
    private Player testPlayer;
    final String username = "username";
    final String character = "Luigi";

    /**
     * Configuration step: this is performed before each test.
     */
    @BeforeEach
    void init() {
        testPlayer = new PlayerImpl(username, character);
    }

    /**
     * Check that the Player implementation is created with the correct values.
     */
    @Test
    void testPlayerInitialization() {
        assertEquals(username, testPlayer.getUsername());
        assertEquals(character, testPlayer.getCharacter().getName());
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
        final Position position = new Position(RANDOM.nextInt(RANDOM_BOUND), RANDOM.nextInt(RANDOM_BOUND));
        this.testPlayer.setPosition(position);
        assertEquals(position, this.testPlayer.getPosition());
    }

    /**
     * Check that player has PlayerBag and Dice correctly initialized.
     */
    @Test
    void playerDiceBag() {
        assertNotNull(this.testPlayer.getPlayerBag());
        assertNotNull(this.testPlayer.getDice());
    }

}
