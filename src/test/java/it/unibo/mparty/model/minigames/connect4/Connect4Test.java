package it.unibo.mparty.model.minigames.connect4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    private static final int ROWS = 6;
    private static final int COLUMN_5 = 5;

    /**
     * Initialize the game before each test.
     */
    @BeforeEach
    public void initGame() {
        testModel = new Connect4ModelImpl();
        testModel.setUpPlayers(List.of("testPlayer1", "testPlayer2"));
    }

    /**
     * Test adding a disc in the minigame.
     */
    @Test
    public void testAddDisc() {
        for (int i = 0; i < ROWS; i++) {
            assertTrue(testModel.addDisc(2));
        }
        assertFalse(testModel.addDisc(2));
    }

    /**
     * Test the horizontal win.
     */
    @Test
    public void checkHorizontalWin() {
        testModel.addDisc(3);
        testModel.addDisc(3);
        testModel.addDisc(4);
        testModel.addDisc(3);
        testModel.addDisc(COLUMN_5);
        testModel.addDisc(3);
        testModel.addDisc(2);
        assertTrue(testModel.isOver());
        assertEquals("testPlayer1", testModel.getTurnPlayer());
    }

    /**
     * Test the vertical win.
     */
    @Test
    public void checkVerticalWin() {
        testModel.addDisc(3);
        testModel.addDisc(2);
        testModel.addDisc(3);
        testModel.addDisc(2);
        testModel.addDisc(3);
        testModel.addDisc(2);
        testModel.addDisc(3);
        assertTrue(testModel.isOver());
    }

    /**
     * Test the diagonal left win.
     */
    @Test
    public void checkDiagonalLeftWin() {
        testModel.addDisc(2);
        testModel.addDisc(3);
        testModel.addDisc(3);
        testModel.addDisc(4);
        testModel.addDisc(4);
        testModel.addDisc(COLUMN_5);
        testModel.addDisc(4);
        testModel.addDisc(COLUMN_5);
        testModel.addDisc(COLUMN_5);
        testModel.addDisc(0);
        testModel.addDisc(COLUMN_5);
        assertTrue(testModel.isOver());
    }

    /**
     * Test the diagonal right win.
     */
    @Test
    public void testDiagonalRightWin() {
        testModel.addDisc(COLUMN_5);
        testModel.addDisc(4);
        testModel.addDisc(4);
        testModel.addDisc(3);
        testModel.addDisc(3);
        testModel.addDisc(2);
        testModel.addDisc(3);
        testModel.addDisc(2);
        testModel.addDisc(2);
        testModel.addDisc(0);
        testModel.addDisc(2);
        assertTrue(testModel.isOver());
    }
}
