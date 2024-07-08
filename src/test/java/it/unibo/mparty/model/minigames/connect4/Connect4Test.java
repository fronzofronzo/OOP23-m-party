package it.unibo.mparty.model.minigames.connect4;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.mparty.model.minigames.connect4.api.Connect4Model;
import it.unibo.mparty.model.minigames.connect4.impl.Connect4ModelImpl;

/**
 * This class tests the {@link Connect4ModelImpl} class.
 */
public class Connect4Test {
    private Connect4Model testModel;

    /**
     * Initialize the game before each test.
     */
    @BeforeEach
    public void initGame() {
        testModel = new Connect4ModelImpl();
        testModel.setUpPlayers(List.of("testPlayer1", "testPlayer2"));
    }

    @Test
    public void testAddDisc() {
        assertTrue(testModel.addDisc(2));
        assertTrue(testModel.addDisc(2));
    }
}
