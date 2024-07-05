package it.unibo.mparty.model.Player;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.api.ItemFactory;
import it.unibo.mparty.model.item.impl.GoldenPipe;
import it.unibo.mparty.model.item.impl.ItemFactoryImpl;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.Character;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import it.unibo.mparty.utilities.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for a {@link PlayerImplementation} class
 */
public class TestPlayerImplementation {

    private Player testPlayer;

    /**
     * Configuration step: this is performed before each test
     */
    @BeforeEach
    public void init(){
        final String username = "username";
        final String character = "Luigi";
        testPlayer = new PlayerImplementation(username,character);
    }

    /**
     * Check that the Player implementation is created with the correct values
     */
    @Test
    public void testPlayerInitialization(){
        assertEquals(0, testPlayer.getNumStars());
        assertEquals(0, testPlayer.getNumCoins());
    }

    /**
     * Check that the operation of star adding and removal work properly
     */
    @Test
    public void testStarAmount(){
        assertEquals(0, testPlayer.getNumStars());
        testPlayer.addStar();
        assertEquals(1, testPlayer.getNumStars());
        testPlayer.removeStar();
        assertEquals(0, testPlayer.getNumStars());
    }

    /**
     * Check that the operation of coins adding and removal work properly
     */
    @Test
    public void testCoinsAmount(){
        final int num_coins = 5;
        assertEquals(0, testPlayer.getNumCoins());
        testPlayer.addCoins(num_coins);
        assertEquals(num_coins, testPlayer.getNumCoins());
        testPlayer.removeCoins(num_coins);
        assertEquals(0, testPlayer.getNumCoins());
    }


}